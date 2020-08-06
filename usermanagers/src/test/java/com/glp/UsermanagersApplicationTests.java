package com.glp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

@SpringBootTest
class UsermanagersApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    public void TestspringVersionAndspringBootVersion (){
        String springBootVersion = SpringBootVersion.getVersion();
        System.out.println(springBootVersion+"++++++++++++++++++++++++++++++++++++++++++====================================");
    }




}
