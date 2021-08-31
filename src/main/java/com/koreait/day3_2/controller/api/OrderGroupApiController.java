package com.koreait.day3_2.controller.api;

import com.koreait.day3_2.ifs.CrudInterface;
import com.koreait.day3_2.model.network.Header;
import com.koreait.day3_2.model.network.request.OrderGroupApiRequest;
import com.koreait.day3_2.model.network.response.AdminApiResponse;
import com.koreait.day3_2.model.network.response.OrderGroupApiResponse;
import com.koreait.day3_2.Service.OrderGroupApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //호출해서 rest로 사용하는 어노테이션
@RequestMapping("/api/ordergroup") //api/user로 호출하면 이쪽으로 오게됨
@RequiredArgsConstructor
public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    private final OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    @PostMapping("")
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
        System.out.println(request);
        return orderGroupApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupApiResponse> read(@PathVariable(name="id") Long id) {
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
        System.out.println(request);
        return orderGroupApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<OrderGroupApiResponse> delete(@PathVariable(name="id") Long id) {
        return orderGroupApiLogicService.delete(id);
    }

    @GetMapping("")     //api/category
    public Header<List<OrderGroupApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return orderGroupApiLogicService.search(pageable);
    }
}
