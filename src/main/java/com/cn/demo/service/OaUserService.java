package com.cn.demo.service;

import com.cn.demo.model.OaUser;

public interface OaUserService {

    int deleteByUserID(String userId);

    int insert(OaUser record);

    int updateUser(OaUser oaUser);

   // int updateByPrimaryKeySelective(OaUser record);



   // OaUser selectByPrimaryKey(String userId);


}