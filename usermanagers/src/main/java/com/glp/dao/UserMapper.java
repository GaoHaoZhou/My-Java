package com.glp.dao;

import com.glp.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface UserMapper {

    //注册
    int register(User registerUser);

    //登录
    User login(User loginUser);

    //增加用户
    int add(User addUser);

    //删除
    int delete( int id);

    //根据id查找
    User find(int id);

    //更新
    int update(User updateUser);


    //查询共有多少条记录
    int findAllRecord(HashMap<String,Object> map);

    //分页查询 map：包含：currentPage、rows、name、address、email
    List<User> findByPage(HashMap<String,Object> map);




}
