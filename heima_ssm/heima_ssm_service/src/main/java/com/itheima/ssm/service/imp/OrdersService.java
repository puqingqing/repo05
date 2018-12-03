package com.itheima.ssm.service.imp;

import com.github.pagehelper.PageHelper;
import com.itheima.domain.Orders;
import com.itheima.ssm.dao.IOrdersDao;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService implements IOrdersService {

    @Autowired
    private IOrdersDao iOrdersDao;
    @Override
    public List<Orders> findAll(int page, int size ) {
        PageHelper.startPage(page,size);
        return iOrdersDao.findAll();
    }

    @Override
    public Orders findById(String id) {
      Orders order=iOrdersDao.findById(id);
        return order;
    }
}
