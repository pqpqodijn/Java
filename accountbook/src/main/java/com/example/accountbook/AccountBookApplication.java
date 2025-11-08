package com.example.accountbook;  
// ✅ 패키지명: src 구조와 반드시 일치해야 함

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 🚀 AccountBookApplication
 * - 프로젝트의 시작점(Main class)
 * - Spring Boot의 내장 Tomcat 서버를 구동시킴
 * - @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
 */
@SpringBootApplication  // ✅ 스프링부트 “자동 설정 + 컴포넌트 스캔” 기능
public class AccountbookApplication {

    public static void main(String[] args) {
        // ✅ Spring Boot 실행 메서드
        SpringApplication.run(AccountbookApplication.class, args);
    }
}