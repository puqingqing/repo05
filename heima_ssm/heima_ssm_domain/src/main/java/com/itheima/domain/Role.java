package com.itheima.domain;

import java.security.Permission;
import java.util.List;

public class Role {
    private String id; //无意义，主键uuid
    private String roleName;//角色名
    private String roleDesc;//角色描述
    private List<Permission01> permissiones;//权限集合
    private List<UserInfo> users;//用户集合

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission01> getPermissiones() {
        return permissiones;
    }

    public void setPermissiones(List<Permission01> permissiones) {
        this.permissiones = permissiones;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", permissiones=" + permissiones +
                ", users=" + users +
                '}';
    }
}
