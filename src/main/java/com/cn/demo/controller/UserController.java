package com.cn.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.demo.model.OaUser;
import com.cn.demo.model.Result;
import com.cn.demo.model.UserLock;
import com.cn.demo.service.OaUserService;
import com.cn.demo.service.impl.OaUserServiceImpl;
import com.cn.demo.util.SaveUtils;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;


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


    /**
     * 修改个人信息
     * author : ick_xy
     * data : 2020-07-11
     */
    @RequestMapping(value = "updataUser" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result updataUser(OaUser oaUser, HttpServletRequest request){
        int i = 0;
        oaUser = getNotStringNullEntity(oaUser,OaUser.class);
        i = userService.updateUserByUserInfo(oaUser);
        OaUser user = userService.getUser(oaUser);
        if( i > 0){
            setUser(request,user);
        }
        return i>0 ? new Result(true,"200","修改成功",new HashMap<>()) : new Result(false,"500","修改失败",new HashMap<>());
    }


    @RequestMapping(value = "login" , method = {RequestMethod.GET , RequestMethod.POST})
    @ResponseBody
    public Result login(String userName,String password){
        Result result = null;
        //登陆前查询 -- 登录账号 userName
        OaUser oaUser = userService.selByuserName(userName);
        if(oaUser!=null){
            UserLock userLock = userService.selIfLock(oaUser.getUserId());
            //锁定次数+1
            if(userLock.getErrorCount()<3){
                int count = userLock.getErrorCount();
                //登录失败次数+1
                if(!oaUser.getPassword().equals(password)){
                    count ++;
                    //登录成功，错误次数归 0
                }else{
                    count = 0;
                }
                userLock.setLockTime(new SimpleDateFormat("yyyy/mm/dd HH:MM:ss").format(new Date(System.currentTimeMillis())));
                userLock.setErrorCount(userLock.getErrorCount()+1);
                userService.updateLock(userLock);
            }

           return userLock.getErrorCount()>3 ?new Result(false,"400","用户已锁定",new HashMap<>()) : !oaUser.getPassword().equals(password) ? new Result(false,"400","用户名或密码不正确",new HashMap<>()) : new Result(true,"200","登录成功",new HashMap<>()) ;
        }
        else{
            result = new Result(false,"400","无此用户",new HashMap<>());
        }
        return result;
    }







    /**
     * 根据传入
     * @param obj
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>T getNotStringNullEntity(Object obj, Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        Object object = null;
        JSONObject js = new JSONObject();
        for(Field field : fields){
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(obj));
             String  value = (String) jsonObject.get(field.getName());
            if(field.getType().isInstance(String.class)&&value.isEmpty()){
                js.put(field.getName(),null);
            }
            js.put(field.getName(),value);
        }
        object = JSON.parseObject(js.toJSONString(),clazz);
        return (T)object;
    }




    /**
     * 注册功能
     * author : ick_xy
     * data : 2020-07-11
     */
    @RequestMapping(value = "getUser",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result setUser(OaUser oaUser, HttpServletRequest request){
        oaUser.setCreateTime(new SimpleDateFormat("yyyy/mm/dd").format(new Date()));
        oaUser.setUpdateTime(new SimpleDateFormat("yyyy/mm/dd").format(new Date()));
        oaUser.setUserId(UUID.randomUUID().toString());
        int i = 0;
        //首先判断用户是否已存在
        OaUser oaUser1 = userService.getUser(oaUser);
        //三目运算符判断是否注册过，并执行注册
        i = oaUser1 == null? userService.saveUser(oaUser) : 0;
        if(i>1){
            setUser(request,oaUser1);
        }
        return i>0 ? new Result(true,"200","添加成功",new HashMap<>()) : new Result(false,"500","添加用户出错",new HashMap<>());
    }


    /**
     * 持久化用户信息
     * @param request
     * @param oaUser
     */
    public static void setUser(HttpServletRequest request,OaUser oaUser){
        //放对象
        Map pr = SaveUtils.getMes(request);
        Map map = new HashMap<String,Object>();
        if(pr != null){
            map = pr;
        }
        map.put("user",oaUser);
        SaveUtils.setMes(request,map);
    }



}
