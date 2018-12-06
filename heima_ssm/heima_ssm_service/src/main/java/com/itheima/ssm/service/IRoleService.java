package com.itheima.ssm.service;

import com.itheima.domain.Permission01;
import com.itheima.domain.Role;

import java.util.List;

public interface IRoleService {
    /**
     * 查询所有的角色
     * @return
     */
    List<Role> findAll();

    /**
     * 添加角色
     * @param role
     */
    void saveOne(Role role);

    /**
     * 查询可以添加的权限有那些
     * @param id
     * @return
     */

    List<Permission01> findOtherRole(String id);

    /**
     * 添加权限
     * @param id
     * @param ids
     */
    void addRoleToUser(String id, String[] ids);
}
