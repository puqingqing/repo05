package com.itheima.ssm.service;

import com.itheima.domain.Permission01;

import java.util.List;

public interface IPermissionService {

    List<Permission01> findAll();

    void saveOne(Permission01 permission01);
}
