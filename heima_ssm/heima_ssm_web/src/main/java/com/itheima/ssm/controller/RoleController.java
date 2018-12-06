package com.itheima.ssm.controller;

import com.itheima.domain.Permission01;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    /**
     * 查询所有的角色
     *
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = iRoleService.findAll();
        mv.addObject("roleList", roles);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String saveOne(Role role) {
        iRoleService.saveOne(role);
        return "redirect:findAll";
    }

    /**
     * 查询可以添加的权限
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public String findUserByIdAndAllRole(String id, Model model) {
        Role role = new Role();
        role.setId(id);
        List<Permission01> permissionList = iRoleService.findOtherRole(id);

        model.addAttribute("role", role);
        model.addAttribute("permissionList", permissionList);

        return "role-permission-add";
    }

    /**
     * 添加权限
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String id, HttpServletRequest request) throws Exception {
        String [] ids = request.getParameterValues("ids");
          iRoleService.addRoleToUser(id,ids);
        return "redirect:findAll";
    }
}
