package com.glp.service;


import com.glp.comment.GenderSelect;
import com.glp.comment.StateSelect;
import com.glp.comment.TitleSelect;
import com.glp.dao.AdminMapper;
import com.glp.dao.TeacherMapper;
import com.glp.pojo.Administrator;
import com.glp.pojo.PageBean;
import com.glp.pojo.Student;
import com.glp.pojo.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;

    //注册
    public int register(Administrator registerUser) {
        return adminMapper.register(registerUser);
    }


    //登录
    public Administrator login(Administrator loginUser) {
        return adminMapper.login(loginUser);
    }


    public int add(Teacher addUser) {
        return adminMapper.add(addUser);
    }


    public int delete(int id) {
        return adminMapper.delete(id);
    }


    public Teacher find(int id) {
        return adminMapper.find(id);
    }


    public int update(Teacher updateUser) {
        return adminMapper.update(updateUser);
    }



    public int findAllRecord(HashMap<String,Object> map) {
        return adminMapper.findAllRecord(map);
    }

    //在service层做逻辑整合

    public PageBean<Teacher> findAllByPage(HashMap<String,Object> map) {
        PageBean<Teacher> pageBean=new PageBean<>();

        int rows = (int)map.get("rows");//rows为每页的行数
        int currentPage = (int)map.get("currentPage");//当前页

        //查询当前搜索中共有多少条记录
        int allRecord = findAllRecord(map);

        //计算总共的页数
        int totalPage;
        if(allRecord%rows==0){
            totalPage=allRecord/rows;
        }else{
            totalPage=allRecord/rows+1;
        }

        //分页查询:返回用户数据，及总页数，当前页数，一页的行数。
        int start=(currentPage-1)*rows;//limit的起点
        map.put("startIndex",start);

        //Mybatis中查询出来的结果，需要进行进一步处理
        List<Teacher> users = adminMapper.findByPage(map);

        //返回前端时，拿到数字对应的字符串：性别和职称
        for(Teacher user : users){
            user.setStitle(TitleSelect.valueOf(user.getTitle()).getStr());
            user.setSgender(GenderSelect.valueOf(user.getGender()).getStr());
        }
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(users);
        pageBean.setRows(rows);
        pageBean.setTotalCount(allRecord);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

//============================学生模块======================================================
public int findAllRecordStudent(HashMap<String,Object> map) {
    return adminMapper.findAllRecordStudent(map);
}
public PageBean<Student> findAllByPageStudent(HashMap<String,Object> map) {
    PageBean<Student> pageBean=new PageBean<>();

    int rows = (int)map.get("rows");//rows为每页的行数
    int currentPage = (int)map.get("currentPage");//当前页

    //查询当前搜索中共有多少条记录
    int allRecord = findAllRecordStudent(map);

    //计算总共的页数
    int totalPage;
    if(allRecord%rows==0){
        totalPage=allRecord/rows;
    }else{
        totalPage=allRecord/rows+1;
    }

    //分页查询:返回用户数据，及总页数，当前页数，一页的行数。
    int start=(currentPage-1)*rows;//limit的起点
    map.put("startIndex",start);

    //Mybatis中查询出来的结果，需要进行进一步处理
    List<Student> users = adminMapper.findByPageStudent(map);

    System.out.println("======================");
    System.out.println(users);
    System.out.println("======================");

    //返回前端时，拿到数字对应的字符串：审核状态
    for(Student user : users){
        user.setState(StateSelect.valueOf(user.getStates()).getStr());
    }
    pageBean.setCurrentPage(currentPage);
    pageBean.setList(users);
    pageBean.setRows(rows);
    pageBean.setTotalCount(allRecord);
    pageBean.setTotalPage(totalPage);
    return pageBean;
}


    public int addStudent(Student addUser) {
        return adminMapper.addStudent(addUser);
    }


    public int deleteStudent(int id) {
        return adminMapper.deleteStudent(id);
    }


    public Student findStudent(int id) {
        return adminMapper.findStudent(id);
    }


    public int updateStudent(Student updateUser) {
        return adminMapper.updateStudent(updateUser);
    }
}
