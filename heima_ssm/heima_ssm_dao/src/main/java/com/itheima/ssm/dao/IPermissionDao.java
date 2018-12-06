package com.itheima.ssm.dao;

import com.itheima.domain.Permission01;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission")
    List<Permission01> findAll();

    @Insert("insert into permission(permissionName,url)values(#{permissionName},#{url})")
    void saveOne(Permission01 permission01);
}
