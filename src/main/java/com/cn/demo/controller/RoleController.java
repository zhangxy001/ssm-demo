package com.cn.demo.controller;

import com.cn.demo.model.Role;
import com.cn.demo.service.impl.RoleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RoleController {
    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RoleServiceImpl roleService;


    @RequestMapping(value = "/insertRole",method = {RequestMethod.GET,RequestMethod.POST})
    public void insertRole(Role role){
        if(null==role){
            logger.info("必传参数为空");
            return;
        }

        Integer count=  roleService.insertRole(role);

        if(count>0){
            logger.info("{}:部门新增成功",role.getRoleName());
        }
        logger.info("{}:部门新增失败",role.getRoleName());


    }

    @RequestMapping(value = "/deleteRole",method = {RequestMethod.GET,RequestMethod.POST})
    public void deleteRole(String roleId){

        Integer count=  roleService.deleteRole(roleId);

        if(count>0){
            logger.info("{}:部门删除成功"+roleId);
        }
        logger.info("{}:部门删除失败");

    }


    @RequestMapping(value = "/updateRole",method = {RequestMethod.GET,RequestMethod.POST})
    public void updateRole(Role role){

        Integer count=  roleService.updateRole(role);

        if(count>0){
            logger.info("{}:部门修改成功");
        }
        logger.info("{}:部门修改失败");


    }


    @RequestMapping(value = "/seleAll",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Role> seleAll(Role role){

        return   roleService.seleAll();

    }


}
