package com.itheima.ssm.dao;

import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
}
