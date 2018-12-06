package com.itheima.ssm.service.imp;

import com.itheima.domain.Permission01;
import com.itheima.domain.Role;
import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public List<Role> findAll() {
      List<Role> roles= iRoleDao.findAll();
        return roles;
    }

    /**
     * 添加角色
     * @param role
     */
    @Override
    public void saveOne(Role role) {
        iRoleDao.saveOne(role);
    }

    /**
     * 查询还可以添加的权限有那些
     * @param id
     * @return
     */
    @Override
    public List<Permission01> findOtherRole(String id) {

        List<Permission01> permission01List= iRoleDao.findOtherRole(id);
        return permission01List;
    }

    /**
     * 添加权限
     * @param
     * @param ids
     */
    @Override
    public void addRoleToUser(String roleId, String[] ids) {
        if(ids.length>0){
            for (String id : ids) {
                iRoleDao.addRoleToUser(roleId,id);
            }
        }
    }


}
