package com.glp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.glp.dao")
public class EdumanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdumanageApplication.class, args);
    }

}
