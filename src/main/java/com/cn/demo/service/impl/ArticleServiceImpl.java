package com.cn.demo.service.impl;

import com.cn.demo.dao.ArticleMapper;
import com.cn.demo.model.Article;
import com.cn.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ick_xy
 * @Date: 2020/07/204
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int save(Article article) {
        return articleMapper.insert(article);
    }

    @Override
    public Article findOneById(Integer id) {
        System.out.println("*******************");
        return articleMapper.selectByPrimaryKey(id);
    }
}
