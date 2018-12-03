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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo usInfo = iUserDao.findOne(username);
        List<Role> roles = usInfo.getRoles();
        User user=new User(usInfo.getUsername(),"{noop}"+usInfo.getPassword(), usInfo.getStatus()==0?false:true,
                true, true,true,getAuthorities(roles) );
        return user;
    }


    public List<SimpleGrantedAuthority> getAuthorities(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return  list;
    }
}
