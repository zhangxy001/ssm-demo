package com.cn.demo.controller;

import com.alibaba.fastjson.JSON;
import com.cn.demo.model.Article;
import com.cn.demo.model.OaUser;
import com.cn.demo.service.ArticleService;
import com.cn.demo.service.impl.ArticleServiceImpl;
import com.cn.demo.util.SaveUtils;
import com.mysql.cj.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ick_xy
 * @Date: 2020/07/204
 */
@RestController
@CrossOrigin
public class IndexController{

    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/")
    public String welcome(HttpServletRequest request) {
        return "success";
    }


    @RequestMapping(value = "/getUserList" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List getUserList(String name,OaUser  oa){

        if(StringUtils.isNullOrEmpty(name)){
            return null;
        }
        //修改的时候
        if(StringUtils.isNullOrEmpty(oa.getUserName())){
            oa.setUserName(null);
        }
        List<OaUser> list = articleService.findAll();
        System.out.println(JSON.toJSONString(list));
        return list;
    }

//   @RequestMapping(value = "/index")
//    public ModelAndView index(HttpServletRequest request, Model model) {
//       String s = "index";
//        //放对象
//        Map pr = SaveUtils.getMes(request);
//        Map map = new HashMap<String,Object>();
//        if(pr != null){
//            map = pr;
//        }
//        map.put("article",new Article());
//        SaveUtils.setMes(request,map);
//        // 取对象
//        Map parm = SaveUtils.getMes(request);
//        if(parm != null){
//            Article article = (Article)parm.get("article");
//        }
//      // articleService.findOneById(1);
//        LOGGER.info(s);
//        LOGGER.warn(s);
//        LOGGER.error(s);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }

}
