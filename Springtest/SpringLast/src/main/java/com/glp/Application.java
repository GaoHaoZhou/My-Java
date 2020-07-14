package com.glp;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.glp.mapper")//查找接口路径
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
