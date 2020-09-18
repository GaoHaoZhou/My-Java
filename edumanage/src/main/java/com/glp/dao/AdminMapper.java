package com.glp.dao;

import com.glp.pojo.Administrator;
import com.glp.pojo.Student;
import com.glp.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface AdminMapper extends baseMapper<Administrator> {
    //注册
    int register(Administrator registerUser);

    //登录
    Administrator login(Administrator loginUser);

//====================导师======================
    //增加导师
    int add(Teacher user);

    //根据id删除人员
    int delete(int id);

    //根据id查找人员
    Teacher find(int id);

    //更新人员信息
    int update(Teacher user);

//====================学生======================
//查找当前查询结果的记录数
    int findAllRecordStudent(HashMap<String,Object> map);

    //返回人员查询结果
    List<Student> findByPageStudent(HashMap<String,Object> map);

    //增加导师
    int addStudent(Student user);

    //根据id删除人员
    int deleteStudent(int id);

    //根据id查找人员
    Student findStudent(int id);

    //更新人员信息
    int updateStudent(Student user);

}
