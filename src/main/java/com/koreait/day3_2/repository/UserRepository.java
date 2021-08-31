package com.koreait.day3_2.repository;


import com.koreait.day3_2.model.entity.DtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //class가 아니라 interface로 상속받아야함 //인터페이스를 상속받아 사용
public interface UserRepository extends JpaRepository<DtUser, Long>{
    //dto에 저장된 데이터들을 JPA를 사용하여 JpaRepository 객체를 만들먄 이 객체에 데이터가 자동으로 저장
    //데이터 테이블과 연결시킬 테이블(Entity),구분할 필드의 타입(long)
    Optional<DtUser> findByUserid(String userid);
                //-> select*from dt_user where userid=? 같은 의미

    Optional<DtUser> findByUseridAndUserpw(String userid, String userpw);//파라미터 순서는 달라도됨
                // -> select*from dt_user where userid=? and userpw=?


    DtUser findFirstByHpOrderByIdDesc(String hp); //객체로 받아오는 메서드
    //Dtuser 객체에 받아옴/ select * from rownum <=1 order by id desc
}

