package com.cn.demo.dao;

import com.cn.demo.model.UserLock;

public interface UserLockMapper {
    int deleteByPrimaryKey(String lockId);

    int insert(UserLock record);

    int insertSelective(UserLock record);

    UserLock selectByPrimaryKey(String lockId);

    int updateByPrimaryKeySelective(UserLock record);

    int updateByPrimaryKey(UserLock record);
}