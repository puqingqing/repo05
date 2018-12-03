package com.itheima.ssm.service;

import com.itheima.domain.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查询产品的所有信息
     * @return
     */
    List<Product> findAll();

    /**
     * 保存数据
     * @param product
     */
    void saveOne(Product product);
}
