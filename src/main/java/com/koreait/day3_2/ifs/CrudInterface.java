package com.koreait.day3_2.ifs;

import com.koreait.day3_2.model.network.Header;

//crud 확장성(규격)을 위한 인터페이스
public interface CrudInterface<Req, Res> {   //각 객체를 제네릭 형태로 받음
    //인터페이스 이므로 -> 상속받는 모든 클래스는 아래 메소드를 생성해야함

    Header<Res> create(Header<Req> request);
    Header<Res> read(Long id);
    Header<Res> update(Header<Req> request);
    Header<Res> delete(Long id);


//    void create(); //데이터를 받아서 DB처리 후 리턴값으로 성공 메세지 전달 ,헤더로 전달받고 헤더로 전달
//                                                        //받는것과 전송 각각의 패키지로 관리(requset,response 패키지)
//
//    void read();
//
//    void update();
//
//    void delete();



}
