package com.itheima.ssm.service.imp;

import com.itheima.domain.Product;
import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductDao iProductDao;

    @Override
    public List<Product> findAll() {

        return iProductDao.findAll();
    }

    @Override
    public void saveOne(Product product) {
        iProductDao.saveOne(product);
    }
}
