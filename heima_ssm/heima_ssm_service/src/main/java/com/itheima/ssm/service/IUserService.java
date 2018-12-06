package com.itheima.ssm.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService{
    /**
     * 查询用户的所有信息
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 添加用户
     * @param userInfo
     */
    void saveOne(UserInfo userInfo);

    /**
     * 查询用户的详细信息
     * @param id
     * @return
     */
   UserInfo findById(String id);

    /**
     * 查询可以添加的角色有那些
     * @param id
     * @return
     */
    List<Role> findOtherRole(String id);

    /**
     * 为用户添加角色
     * @param userId
     * @param ids
     */

    void addRoleToUser(String userId, String [] ids);
}
