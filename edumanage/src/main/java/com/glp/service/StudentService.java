package com.glp.service;

import com.glp.dao.StudentMapper;
import com.glp.dao.TeacherMapper;
import com.glp.pojo.Student;
import com.glp.pojo.Teacher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class StudentService {


    @Value("${student.file.local-path}")
    private String localpath;

    @Resource
    private StudentMapper studentMapper;


    public Student login(Student loginUser) {
        return studentMapper.login(loginUser);
    }


   public int updatePath(Student user){
        return studentMapper.updatePath(user);
   }

    public Student findStudent(Integer id) {
        return studentMapper.findStudent(id);
    }

    public int updateStates(Student student) {
        return studentMapper.updateStates(student);
    }
}
