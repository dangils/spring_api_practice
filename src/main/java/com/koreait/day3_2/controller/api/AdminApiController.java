package com.koreait.day3_2.controller.api;


import com.koreait.day3_2.Service.AdminApiLogicService;
import com.koreait.day3_2.Service.UserApiLogicService;
import com.koreait.day3_2.controller.CrudController;
import com.koreait.day3_2.model.network.Header;
import com.koreait.day3_2.model.network.request.AdminApiRequest;
import com.koreait.day3_2.model.network.request.UserApiRequest;
import com.koreait.day3_2.model.network.response.AdminApiResponse;
import com.koreait.day3_2.model.network.response.ItemApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //호출해서 rest로 사용하는 어노테이션
@RequestMapping("/api/adminuser") //api/user로 호출하면 이쪽으로 오게됨
@RequiredArgsConstructor
public class AdminApiController extends CrudController<AdminApiRequest, AdminApiResponse , KafkaProperties.Admin> {
    private final AdminApiLogicService adminApiLogicService;

    @Override
    @PostMapping("")
    public Header<AdminApiResponse> create(@RequestBody Header<AdminApiRequest> request) {
        System.out.println(request);
        return adminApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<AdminApiResponse> read(@PathVariable(name="id") Long id) {
        //@PathVariable(name="id") : 사용자 전달 이름 , Long id: 여기 메소드에서 쓸 이름
        return adminApiLogicService.read(id); //로직서비스에 id 전달
    }

    @Override
    @PutMapping("")
    public Header<AdminApiResponse>  update(@RequestBody Header<AdminApiRequest> request) {
        System.out.println(request);
        return adminApiLogicService.update(request);
    }


    @Override
    @DeleteMapping("{id}")
    public Header<AdminApiResponse> delete(@PathVariable(name="id") Long id) {
        return adminApiLogicService.delete(id);

    }


    @GetMapping("")     //api/category
    public Header<List<AdminApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return adminApiLogicService.search(pageable);
    }
}
