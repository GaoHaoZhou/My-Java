package com.glp.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

//Controller中，所有处理客户端请求的方法，抛异常都会进入异常处理逻辑
@ControllerAdvice
public class ExceptionAdvisor {

    //返回json数据
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Object handle(Exception e){//Controller方法抛出的异常，会注入到请求参数
//        Map<String, String> map = new HashMap<>();
//        map.put("error", e.getMessage());
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        e.printStackTrace(pw);
//        map.put("stackTrace", sw.toString());
//        return map;
//    }

    @ExceptionHandler(Exception.class)
    public String handle(HttpServletResponse response){//Controller方法抛出的异常，会注入到请求参数
        try {
            response.sendRedirect("/error.html");//通过post请求过来不能直接转发为/error.html，但可以重定向
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
