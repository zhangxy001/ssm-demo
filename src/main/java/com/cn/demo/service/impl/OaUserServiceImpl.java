package com.cn.demo.service.impl;


import com.cn.demo.dao.OaUserMapper;
import com.cn.demo.model.OaUser;
import com.cn.demo.service.OaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionManager;
import javax.transaction.Transactional;
import java.util.TreeSet;

@Service("userService")
@Transactional
public class OaUserServiceImpl implements OaUserService {

    Logger logger= LoggerFactory.getLogger(OaUserServiceImpl.class);

    @Autowired
    private OaUserMapper userMapper;


    @Override
    public int insert(OaUser oaUser) {

        try {
            userMapper.insert(oaUser);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("用户信息新增异常errMessge={}",e.getMessage());
        }


        return 0;
    }

    @Override
    public int updateUser(OaUser oaUser) {
        try {
            userMapper.updateUser(oaUser);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("用户信息新增异常errMessge={}",e.getMessage());
        }
        return 0;
    }



    @Override
    public int deleteByUserID(String userId) {
        try {
            userMapper.deleteByUserID(userId);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("用户信息新增异常errMessge={}",e.getMessage());
        }
        return 0;
    }


}
