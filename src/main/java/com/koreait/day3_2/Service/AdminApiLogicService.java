package com.koreait.day3_2.Service;

import com.koreait.day3_2.model.entity.AdminUser;
import com.koreait.day3_2.model.entity.DtUser;
import com.koreait.day3_2.model.enumclass.DtUserStatus;
import com.koreait.day3_2.model.network.Header;
import com.koreait.day3_2.model.network.request.AdminApiRequest;
import com.koreait.day3_2.model.network.request.UserApiRequest;
import com.koreait.day3_2.model.network.response.AdminApiResponse;
import com.koreait.day3_2.model.network.response.Pagination;
import com.koreait.day3_2.model.network.response.UserApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service //서비스레이어, 내부에서 자바로직을 처리함 [로직: 서비스에서 쓰는 단위테스트]
@RequiredArgsConstructor
public class AdminApiLogicService extends BaseService<AdminApiRequest, AdminApiResponse, AdminUser> {
//DB JPA를 이용해 여기서 처리  //DtUser를 주입해서 userapi로직으로 가져옴


    @Override
    public Header<AdminApiResponse> create(Header<AdminApiRequest> request) {
        AdminApiRequest adminApiRequest = request.getData();
        AdminUser admin = AdminUser.builder()
                .id(adminApiRequest.getId())
                .userid(adminApiRequest.getUserid())
                .userpw(adminApiRequest.getUserpw())
                .name(adminApiRequest.getName())
                .regDate(adminApiRequest.getRegDate())
                .createBy(adminApiRequest.getCreateBy())
                .build();
        AdminUser newadmin = baseRepository.save(admin);
        return Header.OK(response(newadmin));
    }

    @Override
    public Header<AdminApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(admin -> response(admin))
                .map(Header::OK) // 찾앗을때 OK로 보냄
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<AdminApiResponse> update(Header<AdminApiRequest> request) {
        AdminApiRequest adminApiRequest = request.getData();
        Optional<AdminUser> optional = baseRepository.findById(adminApiRequest.getId());
/*
adminRequest의 id로 찾은 값을 jpa로 연결(baseRepository)하여 객체 생성 -> optional


 */


        return optional.map(admin ->{
                    admin.setUserid(adminApiRequest.getUserid());
                    admin.setUserpw(adminApiRequest.getUserpw());
                    admin.setName(adminApiRequest.getName());
                    admin.setRegDate(adminApiRequest.getRegDate());
                    admin.setCreateBy(adminApiRequest.getCreateBy());
                    return admin; // user에 입력된 데이터 반환
                }).map(user -> baseRepository.save(user)) // 데이터 저장 유무에 따라 아래 출려
                .map(user -> response(user))
                .map(Header::OK) // 헤더 OK로 보냄 (::) 메소드 참조 표현식 ( x -> Header.OK(x) )
                .orElseGet(() -> Header.ERROR("데이터 없음"));

        // .map  (특정형태로 변환해서 넘기는 스트림 메소드)


    }

    @Override
    public Header delete(Long id) {
        Optional<AdminUser> optional = baseRepository.findById(id);
        return optional.map(adminUser-> {
            baseRepository.delete(adminUser);
            return  Header.OK();
        }).orElseGet(() -> Header.ERROR("테이터 없음"));
    }

    private AdminApiResponse response(AdminUser adminUser){ //리턴타입 userapiresponse
        AdminApiResponse adminApiResponse = AdminApiResponse.builder()
                .id(adminUser.getId())
                .userid(adminUser.getUserid())
                .userpw(adminUser.getUserpw())
                .name(adminUser.getName())
                .regDate(adminUser.getRegDate())
                .createBy(adminUser.getCreateBy())
                .build();
        return adminApiResponse;

    }


    public Header<List<AdminApiResponse>> search(Pageable pageable){
        Page<AdminUser> adminUsers = baseRepository.findAll(pageable);
        List<AdminApiResponse> adminApiResponseList = adminUsers.stream()
                .map(admin -> response(admin))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(adminUsers.getTotalPages())
                .totalElements(adminUsers.getTotalElements())
                .currentPage(adminUsers.getNumber())
                .currentElements(adminUsers.getNumberOfElements())
                .build();
        return Header.OK(adminApiResponseList, pagination);
    }
}
