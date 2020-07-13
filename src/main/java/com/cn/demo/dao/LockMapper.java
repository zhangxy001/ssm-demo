package com.cn.demo.dao;

import com.cn.demo.model.UserLock;

public interface LockMapper {


    UserLock selIfLock(String userId);

    void updateLock(UserLock userLock);
}
