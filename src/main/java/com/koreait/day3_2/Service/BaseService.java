package com.koreait.day3_2.Service;

import com.koreait.day3_2.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req,Res> {

    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository; //객체를 jpa와 연결해 줄수 있는 기본 레퍼지토리 선언
}
