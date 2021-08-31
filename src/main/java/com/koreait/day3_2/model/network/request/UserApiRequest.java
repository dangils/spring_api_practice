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
// 사용자가 저장할 데이터를필드에 넣고 리퀘스트
public class UserApiRequest { //이곳에 데이터를 받고 DB에 전달하여 insert 시킬 통신용 데이터 클래스
                                // entity에 있는 Dtuser와 유사, 통신에 필요한 데이터를 담음
                                //통신으로 데이터를 전달하므로 어노테이션 필요x
    private Long id;
    private String userid;
    private String userpw;
    private String hp;
    private String email;
    private LocalDateTime regDate;
    private DtUserStatus status;

}
