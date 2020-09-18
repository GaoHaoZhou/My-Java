package com.glp.service;


import com.glp.dao.TeacherMapper;
import com.glp.pojo.PageBean;
import com.glp.pojo.Student;
import com.glp.pojo.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class TeacherService {

    @Resource
    private TeacherMapper teacherMapper;


    public Teacher login(Teacher loginUser) {
        return teacherMapper.login(loginUser);
    }


    public List<Student> getStudents(Teacher teacher) {
        return teacherMapper.getStudents(teacher);
    }
}
