package com.koreait.day3_2.controller.api;


import com.koreait.day3_2.Service.UserApiLogicService;
import com.koreait.day3_2.controller.CrudController;
import com.koreait.day3_2.ifs.CrudInterface;
import com.koreait.day3_2.model.entity.DtUser;
import com.koreait.day3_2.model.network.Header;
import com.koreait.day3_2.model.network.request.UserApiRequest;
import com.koreait.day3_2.model.network.response.UserApiResponse;
import com.koreait.day3_2.model.network.response.UserOrderInfoApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //호출해서 rest로 사용하는 어노테이션
@RequestMapping("/api/user") //api/user로 호출하면 이쪽으로 오게됨
@RequiredArgsConstructor
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse , DtUser> {

    private final UserApiLogicService userApiLogicService; //전역으로 상수 설정

    @Override
    @PostMapping("") // /api/user (post) 방식으로 호출하면 이쪽으로 옴 (http://127.0.0.1/api/User (post) )
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
          //@RequestBody: 리퀘스트(사용자로 부터 전달된 파라미터) 데이터를 받아서 전달하기 위한 어노테이션
        System.out.println(request);
        return userApiLogicService.create(request);

    }



    @Override
    @GetMapping("{id}") // /api/user/{id} 로 호출하면 이쪽으로 옴 (get) 방식
    public Header<UserApiResponse> read(@PathVariable(name="id") Long id) {
                 //@PathVariable(name="id") : 사용자 전달 이름 , Long id: 여기 메소드에서 쓸 이름
        return userApiLogicService.read(id); //로직서비스에 id 전달
    }


//# update
//    {
//        "transaction_time":"2021-08-23",
//            "resultCode" : "OK",
//            "description" : "OK",
//            "data" : {
//        "id" :"102",
//                "userid" : "cherry",
//                "userpw" : "1010",
//                "email" : "cherry@cherry.con",
//                "hp" : "010-3333-3333"
//    }
//    }

    @Override
    @PutMapping("") // /api/user (put)
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        System.out.println(request);
        return userApiLogicService.update(request);
    }



    @Override
    @DeleteMapping("{id}") // /api/user/{id}  아이디를 전달 받아 삭제
    public Header<UserApiResponse> delete(@PathVariable(name="id") Long id) {
        return userApiLogicService.delete(id);

    }

//    @GetMapping("/{id}/orderInfo")  // /api/user/2/orderInfo    -> id값에 대한 쇼핑 정보
//    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id){
//        return userApiLogicService.orderInfo(id);
//    }

    @GetMapping("") // /api/user
    public Header<List<UserApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return userApiLogicService.search(pageable);
    }

}
