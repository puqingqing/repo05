package com.itheima.ssm.service.imp;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * 用户登录的安全和认证
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo usInfo = iUserDao.findOne(username);
        List<Role> roles = usInfo.getRoles();
        User user = new User(usInfo.getUsername(), usInfo.getPassword(), usInfo.getStatus() == 0 ? false : true,
                true, true, true, getAuthorities(roles));
        return user;
    }


    public List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }

        return list;
    }


    /**
     * 查询用户的所有信息
     *
     * @return
     */
    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> users = iUserDao.findAll();
        return users;
    }

    /**
     * 添加用户信息
     * @param userInfo
     */
    @Override
    public void saveOne(UserInfo userInfo) {

        userInfo.setPassword( bCryptPasswordEncoder.encode(userInfo.getPassword()));
               iUserDao.saveOne(userInfo);
    }

    /**
     * 查询用户的详细信息
     * @param id
     * @return
     */
    @Override
    public UserInfo findById(String id) {

       UserInfo userInfos= iUserDao.findById(id);

        return userInfos;
    }

    /**
     * 查询可以添加的角色
     * @param id
     * @return
     */
    @Override
    public List<Role> findOtherRole(String id) {
      List<Role> roles= iUserDao.findOtherRole(id);

        return roles;
    }

    /**
     * 为用户添加角色
     * @param userId
     * @param ids
     */
    @Override
    public void addRoleToUser(String userId, String [] ids) {
        if(ids.length>0){
            for (String id : ids) {
                iUserDao.addRoleToUser(userId,id);
            }
        }


    }
}
