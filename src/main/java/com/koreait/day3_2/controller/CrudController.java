package com.koreait.day3_2.controller;

import com.koreait.day3_2.Service.BaseService;
import com.koreait.day3_2.ifs.CrudInterface;
import com.koreait.day3_2.model.network.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component //타입기반의 자동주입 어노테이션,  Configuration 파일에 Bean을 따로 등록하지 않아도 사용 (빈: get으로 호출할수잇는것?)
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {
                                       //user,partner 등등 의 구분을 위한 Entity

//    //상속 받은 곳에서만 보이게 함
    @Autowired(required = false)
    protected BaseService<Req, Res, Entity> baseService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        return null;
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        return null;
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")
    public Header<Res> delete(@PathVariable Long id) {
        return null;
    }

}
