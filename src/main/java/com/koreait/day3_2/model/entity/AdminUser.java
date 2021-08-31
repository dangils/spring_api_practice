package com.koreait.day3_2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

//lombok에서 지원하는 어노테이션 (라이브러리 이용하는 것)
@Data
@AllArgsConstructor //아래 메소드의 파라미터를 사용하는 생성자 자동생성
@NoArgsConstructor  //빈생성자 자동생성
@Entity             // JPA를 이용하여 필드를 DB와 연결 (클래스와 동일한 이름의 테이블에 연결)
@SequenceGenerator( //오라클에서 만든 시퀀스를 연결
        name="seq_user", //자바에서 사용하는 시퀀스 이름(오라클과 달라도 됨,내가 임의로 만듬)
        sequenceName = "seq_user", // 오라클의 시퀀스 이름
        initialValue = 1, //초기 벨류값
        allocationSize = 1 //증가값
)
@Builder  //메소드 체이닝을 사용할수 있게 해주는 어노테이션
@EntityListeners(AuditingEntityListener.class) //미리 만들어진 auditing 클래스 기능을 사용
public class AdminUser {

    @Id  //아이덴티한 값을 부여하기 위한 어노테이션 (identity 약자) -> 아래 어노테이션과 같이 사용 경우가 많음
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user") //아래 id 필드가 identity 타입 (바로 아랫줄 적용되는 어노테이션)
    private Long id; //일렬번호 //아이덴티한 값을 부여하기 위해 바로 위에 GeneratedValue 어노테이션 선언

    private String userid; //아이디
    private String userpw; //비밀번호
    private String name;   //이름
    private String status;  //상태
    private LocalDateTime lastLoginAt;  // 마지막 접속시간
                        //오라클 상에선 카멜 표기법으로 last_login_at 이름과 같음

    @CreatedDate //기본값으로 regDate가 들어감
    private LocalDateTime regDate;      // 가입 날짜
                        //오라클 상에선 카멜 표기법으로 reg_date 이름과 같음

    @CreatedBy //기본값으로 아래 필드가 들어감
    private String createBy;
}
