package com.koreait.day3_2.controller.api;

import com.koreait.day3_2.ifs.CrudInterface;
import com.koreait.day3_2.model.network.Header;
import com.koreait.day3_2.model.network.request.ItemApiRequest;
import com.koreait.day3_2.model.network.response.CategoryApiResponse;
import com.koreait.day3_2.model.network.response.ItemApiResponse;
import com.koreait.day3_2.Service.ItemApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //호출해서 rest로 사용하는 어노테이션
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    private final ItemApiLogicService itemApiLogicService; //전역으로 상수 설정
   // private final UserApiLogicService userApiLogicService; //전역으로 상수 설정
   // private final ItemApi


    @Override
    @PostMapping("")
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        System.out.println(request);
        return itemApiLogicService.create(request);
    }


    @Override
    @GetMapping("{id}")
    public Header<ItemApiResponse> read(@PathVariable(name="id") Long id) {
        return itemApiLogicService.read(id);
    }

//    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
//        //@RequestBody: 리퀘스트(사용자로 부터 전달된 파라미터) 데이터를 받아서 전달하기 위한 어노테이션
//        System.out.println(request);
//        return userApiLogicService.create(request);
//    }

    @Override
    @PutMapping("")
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        System.out.println(request);
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<ItemApiResponse> delete(@PathVariable(name="id") Long id) {
        return itemApiLogicService.delete(id);
    }

    @GetMapping("")     //api/category
    public Header<List<ItemApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return itemApiLogicService.search(pageable);
    }

}
