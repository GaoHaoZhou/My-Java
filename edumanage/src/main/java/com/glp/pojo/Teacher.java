package com.glp.pojo;

import com.glp.comment.GenderSelect;
import com.glp.comment.TitleSelect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher {
    private Integer id;
    private String teacher_id;
    private String password;
    private String tname;
    private Integer gender;
    private Integer age;
    private Integer title;
    private Date from_date;

    //    返回前端时，拿到数字对应的字符串
    private String sgender;
    private String stitle;




}
