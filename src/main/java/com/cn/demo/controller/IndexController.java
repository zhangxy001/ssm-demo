package com.cn.demo.controller;

import com.cn.demo.model.Article;
import com.cn.demo.service.ArticleService;
import com.cn.demo.service.impl.ArticleServiceImpl;
import com.cn.demo.util.SaveUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
@Controller
public class IndexController{

    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/")
    public String welcome(HttpServletRequest request) {
        return "index";
    }

   @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request, Model model) {
       String s = "index";
        //放对象
        Map pr = SaveUtils.getMes(request);
        Map map = new HashMap<String,Object>();
        if(pr != null){
            map = pr;
        }
        map.put("article",new Article());
        SaveUtils.setMes(request,map);
        // 取对象
        Map parm = SaveUtils.getMes(request);
        if(parm != null){
            Article article = (Article)parm.get("article");
        }
      // articleService.findOneById(1);
        LOGGER.info(s);
        LOGGER.warn(s);
        LOGGER.error(s);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
