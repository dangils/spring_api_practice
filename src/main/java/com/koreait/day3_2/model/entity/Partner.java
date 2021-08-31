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
        name="seq_partner", //자바에서 사용하는 시퀀스 이름(오라클과 달라도 됨,내가 임의로 만듬)
        sequenceName = "seq_partner", // 오라클의 시퀀스 이름
        initialValue = 1, //초기 벨류값
        allocationSize = 1 //증가값
)

@Builder
@EntityListeners(AuditingEntityListener.class)
public class Partner {

    @Id  //아이덴티한 값을 부여하기 위한 어노테이션 (identity 약자) -> 아래 어노테이션과 같이 사용 경우가 많음
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_partner") //아래 id 필드가 identity 타입 (바로 아랫줄 적용되는 어노테이션)

    private Long id;
    private String name;
    private String status;
    private String address;
    private String callCenter;
    private String businessNumber;
    private String ceoName;
    @CreatedDate
    private LocalDateTime regDate;
    @LastModifiedDate
    private LocalDateTime updateDate;
  //  private Long categoryId;

    @ManyToOne //나는 여러개 일수 있고 상대는 한개
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partner")//파트너 테이블을 결합하여
    private List<Item> itemList; //하나의 파트너에 여러개의 아이템 리스트를 저장 가능
}
