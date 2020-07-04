package com.cn.demo.controller;

import com.cn.demo.service.ArticleService;
import com.cn.demo.service.impl.ArticleServiceImpl;
import com.cn.demo.util.SaveUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ick_xy
 * @Date: 2020/07/204
 */
@RestController
public class IndexController implements   ApplicationContextAware{

    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    private ApplicationContext applicationContext;

    private ArticleServiceImpl articleService;

    @GetMapping("/{string}")
    public String index(@PathVariable("string") String s, HttpServletRequest request) {
        //放对象
        Map pr = SaveUtils.getMes(request);
        Map map = new HashMap<String,Object>();
        if(pr != null){
            map = pr;
        }
        map.put("user","");
        SaveUtils.setMes(request,map);
        // 取对象
        Map parm = SaveUtils.getMes(request);
        if(parm != null){
            User user = (User)parm.get("user");
        }
        LOGGER.info(s);
        LOGGER.warn(s);
        LOGGER.error(s);
        articleService.findOneById(1);
        return s;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        articleService = (ArticleServiceImpl) applicationContext.getBean("articleService");
    }
}
