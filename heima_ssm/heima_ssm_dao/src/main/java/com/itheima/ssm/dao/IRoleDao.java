package com.itheima.ssm.dao;

import com.itheima.domain.Permission01;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {
    /**
     * 用户登录的认证
     * @param id
     * @return
     */
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
       List<Role> findById(String id);


    /**
     * 用户详情的查询
     * @param id
     * @return
     */

    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results(
            {@Result(id = true,property = "roleName",column = "ROLENAME"),
                    @Result(property = "roleDesc",column = "ROLEDESC"),
                    @Result(property = "permissiones",column = "id",javaType = List.class,
                            many=@Many(select = "com.itheima.ssm.dao.IPermission.findById")
                    )
            }
    )
    List<Role> findById02(String id);

    /**
     * 查询所有的角色
     * @return
     */
    @Select("select * from role")
    List<Role> findAll();

    /**
     * 添加角色
     * @param role
     */
    @Insert("insert into role(ROLENAME,ROLEDESC)values(#{roleName},#{roleDesc})")
    void saveOne(Role role);

    /**
     * 查询还有那些权限可以添加
     * @param id
     * @return
     */
    @Select("select * from permission where id not in (select PERMISSIONID from role_permission where  ROLEID=#{id})")
    List<Permission01> findOtherRole(String id);

    /**
     * 添加选中的权限
     * @param
     * @param
     */
    @Insert("insert  into role_permission(ROLEID,PERMISSIONID) values (#{ROLEID},#{PERMISSIONID})")
    public void addRoleToUser(@Param("ROLEID") String ROLEID,@Param("PERMISSIONID") String  PERMISSIONID);
}
