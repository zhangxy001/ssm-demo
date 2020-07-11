package com.cn.demo.dao;

import com.cn.demo.model.OaUser;

import java.util.List;

public interface OaUserMapper {


    int deleteByUserID(String userId); //根据Id删除

    int insert(OaUser record);  //增加用户

    int updateUser(OaUser oaUser);  //改


    int insertSelective(OaUser record);


    int updateByPrimaryKey(OaUser record);

    List<OaUser> findAll();

    OaUser getUser(OaUser oaUser);

    int saveUser(OaUser oaUser);

    int updateUserByUserInfo(OaUser oaUser);
}