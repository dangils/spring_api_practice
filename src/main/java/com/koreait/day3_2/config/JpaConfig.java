package com.koreait.day3_2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing //auditing 기능을 키겟다
public class JpaConfig {

}
