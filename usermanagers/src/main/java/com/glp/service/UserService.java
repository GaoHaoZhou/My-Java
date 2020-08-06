package com.glp.service;

import com.glp.pojo.PageBean;
import com.glp.pojo.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    //注册
    int register(User registerUser);

    //登录
    User login(User loginUser);

    //增加用户
    int add(User addUser);

    //删除
    int delete(int id);

    //根据id查找，用来判断user是否存在，如果存在，就继续执行下面的returnFront,及update
    User find(int id);

    //负责将find查到的user，通过session传给前端，在controller中进行处理

    //更新
    int update(User updateUser);


    //查询共有多少条记录
    int findAllRecord(HashMap<String,Object> map);


    //分页查询 map：包含：currentPage、rows、name、address、email
    PageBean<User> findAllByPage(HashMap<String,Object> map);



}
