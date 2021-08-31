package com.koreait.day3_2.Service;

import com.koreait.day3_2.model.entity.DtUser;
import com.koreait.day3_2.model.entity.OrderGroup;
import com.koreait.day3_2.model.enumclass.DtUserStatus;
import com.koreait.day3_2.model.network.Header;
import com.koreait.day3_2.model.network.request.UserApiRequest;
import com.koreait.day3_2.model.network.response.*;
import com.koreait.day3_2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //서비스레이어, 내부에서 자바로직을 처리함 [로직: 서비스에서 쓰는 단위테스트]
@RequiredArgsConstructor


//단위테스트에서 시행했던 내용 여기서 수행
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, DtUser> {
    //DB JPA를 이용해 여기서 처리  //DtUser를 주입해서 userapi로직으로 가져옴


    private final OrderGroupApiLogicService orderGroupApiLogicService;
    private final ItemApiLogicService itemApiLogicService;


    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();
        DtUser user = DtUser.builder()
                .userid(userApiRequest.getUserid())
                .userpw(userApiRequest.getUserpw())
                .hp(userApiRequest.getHp())
                .email(userApiRequest.getEmail())
                .status(DtUserStatus.REGISTERED)
                .build();
        DtUser newUser = baseRepository.save(user);
        return Header.OK(response(newUser));

    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return baseRepository.findById(id)
            .map(user -> response(user))
            .map(Header::OK) // 찾앗을때 OK로 보냄
            .orElseGet(
                    () -> Header.ERROR("데이터 없음")
            );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();
        Optional<DtUser> optional = baseRepository.findById(userApiRequest.getId());

        return optional.map(user ->{
            user.setUserid(userApiRequest.getUserid());
            user.setUserpw(userApiRequest.getUserpw());
            user.setHp(userApiRequest.getHp());
            user.setEmail(userApiRequest.getEmail());
            user.setStatus(userApiRequest.getStatus());
            return user; // user에 입력된 데이터 반환
        }).map(user -> baseRepository.save(user)) // 데이터 저장 유무에 따라 아래 출려
                .map(user -> response(user))
                .map(Header::OK) // 헤더 OK로 보냄
                .orElseGet(() -> Header.ERROR("데이터 없음"));


    }

    @Override
    public Header delete(Long id) {
        Optional<DtUser> optional = baseRepository.findById(id);
        return optional.map(dtUser -> {
            baseRepository.delete(dtUser);
            return  Header.OK();
        }).orElseGet(() -> Header.ERROR("테이터 없음"));
    }

    private UserApiResponse response(DtUser user){ //리턴타입 userapiresponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .userid(user.getUserid())
                .userpw(user.getUserpw())
                .email(user.getEmail())
                .hp(user.getHp())
                .regDate(user.getRegDate())
                .updateDate(user.getUpdateDate())
                .status(user.getStatus())
                .build();
        return userApiResponse;

    }

//    public Header<UserOrderInfoApiResponse> orderInfo(Long id){
//        DtUser user = baseRepository.getById(id);
//        UserApiResponse userApiResponse = response(user);
//
//        List<OrderGroup> orderGroupList = user.getOrderGroupList();
//        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
//                .map(orderGroup -> {
//                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();
//                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
//                            .map(detail -> detail.getItem())
//                           .map(item -> itemApiLogicService.response(item).getDate())
//                            .collect(Collectors.toList()); // collect는 stream의 데이터를 변형등의 처리를 하고 원하는 자료형으로 변환
//
//                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
//                    return orderGroupApiResponse;
//                })
//                .collect(Collectors.toList());
//
//        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
//        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
//                .userApiResponse(userApiResponse)
//                .build();
//        return Header.OK(userOrderInfoApiResponse);
//    }


    public Header<List<UserApiResponse>> search(Pageable pageable){
            Page<DtUser> user = baseRepository.findAll(pageable);
            List<UserApiResponse> userApiResponseList = user.stream()
                    .map(users -> response(users))
                    .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(user.getTotalPages())
                .totalElements(user.getTotalElements())
                .currentPage(user.getNumber())
                .currentElements(user.getNumberOfElements())
                .build();
        return Header.OK(userApiResponseList, pagination);
    }

}
