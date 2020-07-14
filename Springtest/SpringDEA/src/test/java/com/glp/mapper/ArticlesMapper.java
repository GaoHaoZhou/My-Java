package com.glp.mapper;

import com.glp.model.Articles;
import java.util.List;

public interface ArticlesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Articles record);

    Articles selectByPrimaryKey(Integer id);

    List<Articles> selectAll();

    int updateByPrimaryKey(Articles record);
}