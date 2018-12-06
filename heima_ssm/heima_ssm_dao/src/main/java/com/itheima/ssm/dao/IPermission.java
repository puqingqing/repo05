package com.itheima.ssm.dao;

import com.itheima.domain.Permission01;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermission {

    @Select("select * from permission  where id in(select PERMISSIONID from role_permission where ROLEID=#{ROLEID})")
    List<Permission01> findById(String id);
}
