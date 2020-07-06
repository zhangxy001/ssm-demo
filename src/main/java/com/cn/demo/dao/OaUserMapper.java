package com.cn.demo.dao;

import com.cn.demo.model.OaUser;

public interface OaUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(OaUser record);

    int insertSelective(OaUser record);

    OaUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(OaUser record);

    int updateByPrimaryKey(OaUser record);
}