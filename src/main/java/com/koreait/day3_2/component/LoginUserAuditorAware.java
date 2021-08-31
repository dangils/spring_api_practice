package com.koreait.day3_2.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() { //받은 string을 옵셔널로 받아 getCurrent 실행하여 optional로 리턴
        return Optional.of("최고관리자"); //최고관리자가 무조건 들어가게됨
    }
}
