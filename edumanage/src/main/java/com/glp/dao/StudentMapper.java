package com.glp.dao;

import com.glp.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentMapper extends baseMapper<Student> {

    int updatePath(Student student);

    int updateStates(Student student);
    Student findStudent(Integer id);
}
