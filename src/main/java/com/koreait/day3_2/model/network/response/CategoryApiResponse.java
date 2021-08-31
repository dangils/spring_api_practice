package com.koreait.day3_2.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryApiResponse {

    private Long id;
    private String type;
    private String title;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private LocalDateTime updateBy;
}
