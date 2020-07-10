package com.cn.demo.service.impl;

import com.cn.demo.dao.RoleMapper;
import com.cn.demo.model.Role;
import com.cn.demo.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("roleServiceImpl")
@Transactional
public class RoleServiceImpl implements RoleService {
    Logger logger= LoggerFactory.getLogger(OaUserServiceImpl.class);


    @Autowired
    private RoleMapper roleMapper;


    @Override
    public int insertRole(Role role) {
        try {
            roleMapper.insertRole(role);

        }catch (Exception e){
            e.printStackTrace();
            logger.info("部门信息新增异常errMessge={}",e.getMessage());
        }
        return 0;
    }





    @Override
    public int deleteRole(String roleId) {
        try {
            roleMapper.deleteRole(roleId);

        }catch (Exception e){
            e.printStackTrace();
            logger.info("部门信息新增异常errMessge={}",e.getMessage());
        }
        return 0;
    }

    @Override
    public int updateRole(Role role) {
        roleMapper.updateRole(role);
        return 0;
    }

    @Override
    public List<Role> seleAll() {
        return roleMapper.seleAll();
    }
}
