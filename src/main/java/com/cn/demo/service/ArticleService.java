package com.cn.demo.service;

import com.cn.demo.model.Article;
import com.cn.demo.model.OaUser;

import java.util.List;

/**
 * @Author: ick_xy
 * @Date: 2020/07/204
 */
public interface ArticleService {

    public int save(Article article);

    public Article findOneById(Integer id);

    List<OaUser> findAll();
}
