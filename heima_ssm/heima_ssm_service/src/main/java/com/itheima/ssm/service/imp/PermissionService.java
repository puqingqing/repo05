package com.itheima.ssm.service.imp;

import com.itheima.domain.Permission01;
import com.itheima.ssm.dao.IPermissionDao;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService implements IPermissionService{

    @Autowired
   private IPermissionDao iPermissionDao;
    @Override
    public List<Permission01> findAll() {
        return iPermissionDao.findAll();
    }

    @Override
    public void saveOne(Permission01 permission01) {
        iPermissionDao.saveOne(permission01);
    }
}
