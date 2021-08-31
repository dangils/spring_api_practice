package com.koreait.day3_2.Service;

import com.koreait.day3_2.ifs.CrudInterface;
import com.koreait.day3_2.model.entity.Category;
import com.koreait.day3_2.model.entity.DtUser;
import com.koreait.day3_2.model.enumclass.DtUserStatus;
import com.koreait.day3_2.model.network.Header;
import com.koreait.day3_2.model.network.request.CategoryApiRequest;
import com.koreait.day3_2.model.network.request.ItemApiRequest;
import com.koreait.day3_2.model.network.request.UserApiRequest;
import com.koreait.day3_2.model.network.response.CategoryApiResponse;
import com.koreait.day3_2.model.network.response.ItemApiResponse;
import com.koreait.day3_2.model.network.response.Pagination;
import com.koreait.day3_2.model.network.response.UserApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //서비스레이어, 내부에서 자바로직을 처리함 [로직: 서비스에서 쓰는 단위테스트]
@RequiredArgsConstructor

public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {


    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();
        Category category = Category.builder()
                .id(categoryApiRequest.getId())
                .type(categoryApiRequest.getType())
                .title(categoryApiRequest.getTitle())
                .build();
        Category newCategory = baseRepository.save(category);
        return Header.OK(response(newCategory));
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(category -> response(category))
                .map(Header::OK) // 찾앗을때 OK로 보냄
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();
        Optional<Category> optional = baseRepository.findById(categoryApiRequest.getId());

        return optional.map(category ->{
                    category.setId(categoryApiRequest.getId());
                    category.setTitle(categoryApiRequest.getTitle());
                    category.setType(categoryApiRequest.getType());

                    return category; // user에 입력된 데이터 반환
                }).map(category -> baseRepository.save(category)) // 데이터 저장 유무에 따라 아래 출려
                .map(category -> response(category))
                .map(Header::OK) // 헤더 OK로 보냄
                .orElseGet(() -> Header.ERROR("데이터 없음"));


    }

    @Override
    public Header delete(Long id) {
        Optional<Category> optional = baseRepository.findById(id);
        return optional.map(category -> {
            baseRepository.delete(category);
            return  Header.OK();
        }).orElseGet(() -> Header.ERROR("테이터 없음"));
    }

    private CategoryApiResponse response(Category category){
        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .id(category.getId())
                .type(category.getType())
                .title(category.getTitle())
                .regDate(category.getRegDate())
                .build();
        return categoryApiResponse;
    }


    public Header<List<CategoryApiResponse>> search(Pageable pageable){
        Page<Category> category= baseRepository.findAll(pageable);
        List<CategoryApiResponse> categoryApiResponses = category.stream()
                .map(categories -> response(categories))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(category.getTotalPages())
                .totalElements(category.getTotalElements())
                .currentPage(category.getNumber())
                .currentElements(category.getNumberOfElements())
                .build();
        return Header.OK(categoryApiResponses, pagination);
    }


}
