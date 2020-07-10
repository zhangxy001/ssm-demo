package com.cn.demo.dao;

import com.cn.demo.model.Role;

import java.util.List;

public interface RoleMapper {
    int insertRole(Role role);
    int deleteRole(String roleId);
    int updateRole(Role role);
    List<Role> seleAll();
}
