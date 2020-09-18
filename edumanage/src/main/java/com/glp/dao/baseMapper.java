package com.glp.dao;

import com.glp.pojo.Administrator;
import com.glp.pojo.Teacher;

import java.util.HashMap;
import java.util.List;

public interface baseMapper<T> {

    //登录
    T login(T loginUser);

    //根据id删除人员
    int delete(int id);

    //查找当前查询结果的记录数
    int findAllRecord(HashMap<String,Object> map);

    //返回人员查询结果
    List<Teacher> findByPage(HashMap<String,Object> map);
}
