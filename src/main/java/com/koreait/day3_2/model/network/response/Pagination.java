package com.koreait.day3_2.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.Inet4Address;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {
    private Integer totalPages;
    private Long totalElements;
    private Integer currentPage;
    private Integer currentElements;

}
