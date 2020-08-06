package com.glp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String gender;
    private int age;
    private String address;
    private String qq;
    private String email;
}
