package com.itheima.ssm.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface IUserDao {

    @Select("select * from USERS where USERNAME=#{username} ")
    @Results(
            {@Result(id = true, property = "id", column = "id"),
                    @Result(property = "email", column = "email"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "phoneNum", column = "phoneNum"),
                    @Result(property = "status", column = "status"),
                    @Result(property = "roles", column = "id", javaType = List.class,
                            many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findById")
                    )}
    )
    UserInfo findOne(String username);


    @Select("select * from USERS")
    List<UserInfo> findAll();


    @Insert("insert into USERS(email,username,password,phoneNum,status)values(#{email},#{username},#{password},#{phoneNum},#{status}) ")
    void saveOne(UserInfo userInfo);

    @Select("select * from USERS where id=#{id} ")
    @Results(
            {@Result(id = true, property = "id", column = "id"),
                    @Result(property = "email", column = "email"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "phoneNum", column = "phoneNum"),
                    @Result(property = "status", column = "status"),
                    @Result(property = "roles", column = "id", javaType = List.class,
                            many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findById02")
                    )}
    )
    UserInfo findById(String id);

    /**
     * 查询可以添加的角色
     * @param id
     * @return
     */

    @Select("select * from ROLE where id not in (select ROLEID from USERS_ROLE where  USERID=#{userId})")
    List<Role> findOtherRole(String id);


    /**
     * 用户添加角色
     * @param
     * @param
     */
    @Insert("insert  into USERS_ROLE(USERID,ROLEID) values (#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId,@Param("roleId") String  roleId);

    /*@Insert("insert into user_role(userId,roleId) value(#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId, @Param("roleId") String[] roleId);*/
}
