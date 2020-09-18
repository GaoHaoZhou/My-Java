package com.glp.dao;

import com.glp.pojo.Student;
import com.glp.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TeacherMapper extends baseMapper<Teacher> {

    List<Student> getStudents(Teacher teacher);
}
