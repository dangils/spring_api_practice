package com.koreait.day3_2.model.network.response;
import com.koreait.day3_2.model.enumclass.DtUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiResponse {

    private Long id;
    private String userid;
    private String userpw;
    private String hp;
    private String email;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private DtUserStatus status;

    private List<OrderGroupApiResponse> orderGroupApiResponseList;  //get ,set 가능

}
