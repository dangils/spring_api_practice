package com.koreait.day3_2.model.network;


import com.koreait.day3_2.model.network.response.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 사이트에 url 입력후 서버에 접근, 접근 한 api로 메소드 호출, rest에서 json으로 넘겨 받음
// json형태로 전달받기 위한 양식을 생성하는 클래스
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {
                        //user,partner,oderGroup..등 다양하므로, 제네릭으로 탬플릿을 받아 처리
                          // rest에서 아래 규격으로 데이터를 전달
    
    // api 통신시간
    private LocalDateTime transactionTime;
    // api 응답코드
    private String resultCode;
    // api 설명
    private String description;
    
    private  T data;  // 제네릭 설정한 타입의 데이터

    private  Pagination pagination;



    // ok (insert, delete) 정상 작동 확인해주는 메소드
    public static <T> Header<T> OK(){  //Header<T> 형태로 데이터 리턴 (위에 설정한 규격의 제이슨 형태로 리턴)
        return(Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // Data 리턴해주는 메소드
    public static <T> Header<T> OK(T data){
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    public static <T> Header<T> OK(T data, Pagination pagination){
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .pagination(pagination)
                .build();
    }



    // error 에러 리턴 메소드
    public static <T> Header<T> ERROR(String description){
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description("ERROR")
                .build();
    }

}
