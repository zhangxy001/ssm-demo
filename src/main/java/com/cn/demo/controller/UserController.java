package com.cn.demo.controller;

import com.cn.demo.model.OaUser;
import com.cn.demo.service.OaUserService;
import com.cn.demo.service.impl.OaUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private OaUserService userService;

    @RequestMapping(value = "/insert",method = {RequestMethod.GET,RequestMethod.POST})
    public void insert(OaUser oaUser){
        if(null==oaUser){
            logger.info("必传参数为空");
            return;
        }

     Integer count=  userService.insert(oaUser);

     if(count>0){
         logger.info("{}:用户新增成功",oaUser.getUserName());
     }
        logger.info("{}:用户新增失败",oaUser.getUserName());


    }

    @RequestMapping(value = "/updateUser",method = {RequestMethod.GET,RequestMethod.POST})
    public void updateUser(OaUser oaUser){


        Integer count=  userService.updateUser(oaUser);

        if(count>0){
            logger.info("{}:用户修改成功",oaUser.getUserName());
        }
        logger.info("{}:用户修改失败",oaUser.getUserName());


    }

    @RequestMapping(value = "/deleteByUserID",method = {RequestMethod.GET,RequestMethod.POST})
    public void deleteByUserID(String   userId){


        Integer count=  userService.deleteByUserID(userId);

        if(count>0){
            logger.info("{}:用户删除成功" );
        }
        logger.info("{}:用户删除失败" );


    }



}
