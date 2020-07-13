package com.cn.demo.service;

import com.cn.demo.model.OaUser;
import com.cn.demo.model.UserLock;

public interface OaUserService {

    int deleteByUserID(String userId);

    int insert(OaUser record);

    int updateUser(OaUser oaUser);

    OaUser getUser(OaUser oaUser);

    int saveUser(OaUser oaUser);

    int updateUserByUserInfo(OaUser oaUser);

    OaUser selByuserName(String userName);

    UserLock selIfLock(String userId);

    void updateLock(UserLock userLock);

    // int updateByPrimaryKeySelective(OaUser record);



   // OaUser selectByPrimaryKey(String userId);


}
