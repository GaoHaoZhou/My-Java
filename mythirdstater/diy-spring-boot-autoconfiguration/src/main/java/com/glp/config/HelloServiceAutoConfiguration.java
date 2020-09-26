package com.glp.config;

import com.glp.properties.Properties;
import com.glp.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(Properties.class)
public class HelloServiceAutoConfiguration {
    @Autowired
    Properties properties;

    @Bean
    public HelloService getHelloService(){
        HelloService service = new HelloService();
        service.setProperties(properties);
        return service;
    }
}
