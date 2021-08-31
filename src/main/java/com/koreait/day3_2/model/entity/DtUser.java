package com.koreait.day3_2.model.entity;

import com.koreait.day3_2.model.enumclass.DtUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

@Builder
@EntityListeners(AuditingEntityListener.class)
public class DtUser {
    @Id  //아이덴티한 값을 부여하기 위한 어노테이션 (identity 약자) -> 아래 어노테이션과 같이 사용 경우가 많음
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    //아래 id 필드가 identity 타입 (바로 아랫줄 적용되는 어노테이션)

    private Long id;
    private String userid;
    private String userpw;
    private String hp;
    private String email;
    @CreatedDate
    private LocalDateTime regDate;
    @LastModifiedDate
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private DtUserStatus status; //REGISTERED, UNREGISTERED

    //나는 한개인데 보더 그룹이 여러개       //테이블 이름
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dtUser")
    private List<OrderGroup> orderGroupList;


}
