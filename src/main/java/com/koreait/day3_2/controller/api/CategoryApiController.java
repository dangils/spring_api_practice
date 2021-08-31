package com.koreait.day3_2.controller.api;

import com.koreait.day3_2.Service.CategoryApiLogicService;
import com.koreait.day3_2.controller.CrudController;
import com.koreait.day3_2.model.entity.Category;
import com.koreait.day3_2.model.network.Header;
import com.koreait.day3_2.model.network.request.CategoryApiRequest;
import com.koreait.day3_2.model.network.response.CategoryApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //호출해서 rest로 사용하는 어노테이션
@RequestMapping("/api/category") //api/user로 호출하면 이쪽으로 오게됨
@RequiredArgsConstructor
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse , Category>{
    private  final CategoryApiLogicService categoryApiLogicService;

    @Override
    @PostMapping("")
    public Header<CategoryApiResponse> create(@RequestBody Header<CategoryApiRequest> request) {
        System.out.println(request);
        return categoryApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<CategoryApiResponse> read(@PathVariable(name="id") Long id) {
        return categoryApiLogicService.read(id); //로직서비스에 id 전달
    }

    @Override
    @PutMapping("")
    public Header<CategoryApiResponse> update(@RequestBody Header<CategoryApiRequest> request) {
        System.out.println(request);
        return categoryApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<CategoryApiResponse> delete(@PathVariable(name="id") Long id) {
        return categoryApiLogicService.delete(id);
    }

    @GetMapping("")     //api/category
    public Header<List<CategoryApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return categoryApiLogicService.search(pageable);
    }
}

