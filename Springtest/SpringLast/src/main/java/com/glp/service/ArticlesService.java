package com.glp.service;


import com.glp.mapper.ArticlesMapper;
import com.glp.model.Articles;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticlesService {
    @Resource
    private ArticlesMapper articlesMapper;

    public Articles queryById(Integer id) {
        return articlesMapper.selectByPrimaryKey(id);
    }

    public List<Articles> queryByUserId(Integer userId) {
        return articlesMapper.queryByUserId(userId);
    }
}
