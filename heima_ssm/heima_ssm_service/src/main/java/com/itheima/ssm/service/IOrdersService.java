package com.itheima.ssm.service;

import com.itheima.domain.Orders;

import java.util.List;

public interface IOrdersService {

    /**
     * 查询订单的所有
     * @param page
     * @param size
     * @return
     */
    List<Orders> findAll(int page,int size);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Orders findById(String id);
}
