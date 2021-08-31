package com.koreait.day3_2.model.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator( //오라클에서 만든 시퀀스를 연결
        name="seq_cate", //자바에서 사용하는 시퀀스 이름(오라클과 달라도 됨,내가 임의로 만듬)
        sequenceName = "seq_cate", // 오라클의 시퀀스 이름
        initialValue = 1, //초기 벨류값
        allocationSize = 1 //증가값
)

@Builder
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id  //아이덴티한 값을 부여하기 위한 어노테이션 (identity 약자) -> 아래 어노테이션과 같이 사용 경우가 많음
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cate") //아래 id 필드가 identity 타입 (바로 아랫줄 적용되는 어노테이션)

    private Long id;
    private String type;
    private String title;

    @CreatedDate
    private LocalDateTime regDate;

    @LastModifiedDate  //마지막 수정날짜짜
    private LocalDateTime updateDate;

    @LastModifiedDate
    private String updateBy;
    //나는 하나이고 연결된 객체는 여러개 일때 1:다 연결
    @OneToMany(fetch= FetchType.LAZY, mappedBy = "category")
    private List<Partner> partnerList;

}
