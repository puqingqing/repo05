package com.itheima.ssm.controller;

import com.itheima.domain.Permission01;
import com.itheima.domain.Role;

import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PremissionController {

    @Autowired
    private IPermissionService iPermission;

    /**
     * 权限的查询
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Permission01> permission01s = iPermission.findAll();
        mv.addObject("permissionList",permission01s);
        mv.setViewName("permission-list");
        return  mv;
    }

    @RequestMapping("/save")
    public String saveOne(Permission01 permission01){
        iPermission.saveOne(permission01);
        return "redirect:findAll";
    }
}
