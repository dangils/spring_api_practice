package com.koreait.day3_2.controller.api;

import com.koreait.day3_2.ifs.CrudInterface;
import com.koreait.day3_2.model.network.Header;
import com.koreait.day3_2.model.network.request.PartnerApiRequest;
import com.koreait.day3_2.model.network.response.PartnerApiResponse;
import com.koreait.day3_2.Service.PartnerApiLogicService;
import com.koreait.day3_2.model.network.response.UserApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //호출해서 rest로 사용하는 어노테이션
@RequestMapping("/api/partner") //api/user로 호출하면 이쪽으로 오게됨
@RequiredArgsConstructor
public class PartnerApiController implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

    private final PartnerApiLogicService partnerApiLogicService;

    @Override
    @PostMapping("")
    public Header<PartnerApiResponse> create(@RequestBody Header<PartnerApiRequest> request) {
        System.out.println(request);
        return partnerApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<PartnerApiResponse> read(@PathVariable Long id) {
        return partnerApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<PartnerApiResponse> update(@RequestBody Header<PartnerApiRequest> request) {
        return partnerApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<PartnerApiResponse> delete(@PathVariable(name="id") Long id) {
        return partnerApiLogicService.delete(id);
    }

    @GetMapping("") // /api/user
    public Header<List<PartnerApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return partnerApiLogicService.search(pageable);
    }


}
