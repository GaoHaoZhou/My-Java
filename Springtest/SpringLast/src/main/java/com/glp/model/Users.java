package com.glp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public class Users {
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String brief;

    private Date registeredAt;
}