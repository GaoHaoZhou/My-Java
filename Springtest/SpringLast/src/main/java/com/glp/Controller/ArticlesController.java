package com.glp.Controller;


import com.glp.model.Articles;
import com.glp.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticlesController {
    @Autowired
    private ArticlesService articlesService;

    @RequestMapping("/query/{id}")
    public Object queryById(@PathVariable("id") Integer id){
        Articles article = articlesService.queryById(id);
        return article;
    }

    @RequestMapping("/queryByUserId")
    public Object queryByUserId(Integer userId){
        List<Articles> articlesList=articlesService.queryByUserId(userId);
        return articlesList;
    }

}
