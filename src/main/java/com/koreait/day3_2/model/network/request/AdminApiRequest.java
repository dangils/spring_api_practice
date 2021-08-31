package com.koreait.day3_2.model.network.request;

import com.koreait.day3_2.model.enumclass.DtUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminApiRequest {
    private Long id;
    private String userid;
    private String userpw;
    private String name;
    private LocalDateTime regDate;
    private String createBy;
}
