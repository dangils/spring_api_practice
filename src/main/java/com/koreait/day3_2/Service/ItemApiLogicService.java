package com.koreait.day3_2.Service;

import com.koreait.day3_2.ifs.CrudInterface;
import com.koreait.day3_2.model.entity.Item;
import com.koreait.day3_2.model.enumclass.ItemStatus;
import com.koreait.day3_2.model.network.Header;
import com.koreait.day3_2.model.network.request.ItemApiRequest;
import com.koreait.day3_2.model.network.response.ItemApiResponse;
import com.koreait.day3_2.model.network.response.Pagination;
import com.koreait.day3_2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //서비스레이어, 내부에서 자바로직을 처리함 [로직: 서비스에서 쓰는 단위테스트]
@RequiredArgsConstructor
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    private ItemRepository itemRepository; //확인

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        ItemApiRequest itemApiRequest = request.getData();
        Item item = Item.builder()
                .id(itemApiRequest.getId())
                .name(itemApiRequest.getName())
                .status(ItemStatus.REGISTERED)
                .title(itemApiRequest.getTitle())
                .content(itemApiRequest.getContent())
                .price(itemApiRequest.getPrice())
                .build();

        Item newitem = baseRepository.save(item);
        return Header.OK(response(newitem));

    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return itemRepository.findById(id)
                .map(item -> response(item))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        Optional<Item> optional = baseRepository.findById(itemApiRequest.getId());

        return optional.map(item -> {
            item.setId(itemApiRequest.getId());
            item.setName(itemApiRequest.getName());
            item.setTitle(itemApiRequest.getTitle());
            item.setContent(itemApiRequest.getContent());
            item.setPrice(itemApiRequest.getPrice());
            return item;
        }).map(item -> baseRepository.save(item))
                .map(item -> response(item))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Item> optional = baseRepository.findById(id);
        return optional.map(item -> {
            baseRepository.delete(item);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    public ItemApiResponse response(Item item){
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .regDate(item.getRegDate())
                .build();
        return itemApiResponse;
    }

        public Header<List<ItemApiResponse>> search(Pageable pageable){
            Page<Item> item = baseRepository.findAll(pageable);
            List<ItemApiResponse> itemApiResponseList = item.stream()
                    .map(items -> response(items))
                    .collect(Collectors.toList());

            Pagination pagination = Pagination.builder()
                    .totalPages(item.getTotalPages())
                    .totalElements(item.getTotalElements())
                    .currentPage(item.getNumber())
                    .currentElements(item.getNumberOfElements())
                    .build();

            return Header.OK(itemApiResponseList, pagination);


        }
}
