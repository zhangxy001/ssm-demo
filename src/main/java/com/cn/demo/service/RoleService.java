package com.cn.demo.service;

import com.cn.demo.model.Role;

import java.util.List;

public interface RoleService {
    int insertRole(Role role);//+
    int deleteRole(String roleId);
    int updateRole(Role role);
    List<Role> seleAll();
}
