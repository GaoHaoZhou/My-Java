package com.example.config;


import com.example.properties.HelloProperties;
import com.example.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnWebApplication //web应用
//注册配置
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {
    @Autowired
    HelloProperties helloProperties;




    @Bean
    public HelloService helloService(){
        HelloService helloService= new HelloService();
        helloService.setHelloProperties(helloProperties);
        return helloService;
    }
}
