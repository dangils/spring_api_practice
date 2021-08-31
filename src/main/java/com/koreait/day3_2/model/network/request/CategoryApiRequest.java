package com.koreait.day3_2.model.network.request;


import com.koreait.day3_2.model.enumclass.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryApiRequest {
    private Long id;
    private String type;
    private String title;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private LocalDateTime updateBy;

}
