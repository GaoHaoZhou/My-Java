package com.glp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Articles {
    private Integer id;

    private String coverImage;

    private Integer authorId;

    private String title;

    private String body;

    private Date publishedAt;

    private Users user;
}