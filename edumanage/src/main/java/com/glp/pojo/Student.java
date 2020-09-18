package com.glp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private Integer id;
    private String student_id;
    private String password;
    private String sname;
    private Integer teacher_id;
    private String tname;
    private Integer states;
    private String message;
    private String state;
}
