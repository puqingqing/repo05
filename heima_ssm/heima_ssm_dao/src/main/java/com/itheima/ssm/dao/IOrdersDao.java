package com.itheima.ssm.dao;

import com.itheima.domain.Member;
import com.itheima.domain.Orders;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IOrdersDao {

    /**
     * 查询所有
     * @return
     */
    @Select("select * from orders")
    @Results(
            {@Result(id = true,property = "id",column = "id"),
            @Result(property ="orderNum" ,column ="orderNum" ),
            @Result(property ="orderTime" ,column ="orderTime" ),
            @Result(property ="orderStatus" ,column ="orderStatus" ),
            @Result(property ="peopleCount" ,column ="peopleCount" ),
            @Result(property ="payType" ,column ="payType" ),
            @Result(property ="orderDesc" ,column ="orderDesc" ),
                    @Result(property = "product",column = "productId",javaType = Product.class,
                            one = @One(select = "com.itheima.ssm.dao.IProductDao.findById"))

            } )
    List<Orders> findAll();

    /**
     * 根据id查询订单的详细信息
     * @param id
     * @return
     */
    @Select("select * from orders where id=#{id}")
    @Results(
            {@Result(id = true,property = "id",column = "id"),
                    @Result(property ="orderNum" ,column ="orderNum" ),
                    @Result(property ="orderTime" ,column ="orderTime" ),
                    @Result(property ="orderStatus" ,column ="orderStatus" ),
                    @Result(property ="peopleCount" ,column ="peopleCount" ),
                    @Result(property ="payType" ,column ="payType" ),
                    @Result(property ="orderDesc" ,column ="orderDesc" ),
                    @Result(property = "product",column = "productId",javaType = Product.class,
                            one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")),

                    @Result(property = "member",column = "memberId",javaType =Member.class ,
                    one=@One(select = "com.itheima.ssm.dao.IMemberDao.findById")),

                    @Result(property = "travellers",column = "id",
                            many = @Many(select = ("com.itheima.ssm.dao.ITravellerDao.findByOrderId")))

            } )
    Orders findById(String id);
}
