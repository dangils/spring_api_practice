package com.koreait.day3_2.model.network.response;

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

public class AdminApiResponse {

    private Long id;
    private String userid;
    private String userpw;
    private String name;
    private LocalDateTime regDate;
    private String createBy;
}
