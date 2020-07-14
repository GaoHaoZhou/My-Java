package com.glp.mapper;

import com.glp.model.Articles;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticlesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Articles record);

    Articles selectByPrimaryKey(Integer id);

    List<Articles> selectAll();

    int updateByPrimaryKey(Articles record);

    List<Articles> queryByUserId(Integer userId);
}