package com.glp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {
    private int totalCount; //总记录数
    private int totalPage; //总页码
    private List<T> list; //每页中的数据
    private int currentPage;//当前页码
    private int rows; //每页的记录数
}
