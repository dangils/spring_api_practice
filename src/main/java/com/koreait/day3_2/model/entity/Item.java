
package com.koreait.day3_2.model.entity;

import com.koreait.day3_2.model.enumclass.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name="seq_item",
        sequenceName = "seq_item",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item")
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ItemStatus status;
    private String title;
    private String content;
    private BigDecimal price;

    @CreatedDate
    private LocalDateTime regDate;
    @CreatedBy
    private String createBy;
    @LastModifiedDate
    private LocalDateTime updateDate;
    @LastModifiedDate
    private String updateBy;

   // private Long partnerId;

//파트너 아이디에서 여러개의 아이템을 팔으므로
    @ManyToOne
    private Partner partner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;


}