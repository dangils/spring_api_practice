package com.koreait.day3_2.model.network.request;

import com.koreait.day3_2.model.enumclass.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ItemApiRequest {
    private Long id;
    private String name;
    private ItemStatus status;
    private String title;
    private String content;
    private BigDecimal price;
}
