package com.itheima.ssm.controller;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private IUserService iUserService;

    /**
     * 添加角色
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String id, HttpServletRequest request) throws Exception {
        String [] ids = request.getParameterValues("ids");
        iUserService.addRoleToUser(id,ids);
        return "redirect:findAll";
    }



    /**
     * 查询用户可以添加的角色有那些
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public String findUserByIdAndAllRole(String id, Model model) {
        UserInfo userInfo=new UserInfo();
        userInfo.setId(id);
        List<Role> roles = iUserService.findOtherRole(id);

        model.addAttribute("user",userInfo );
        model.addAttribute("roleList", roles);

        return "user-role-add";
    }


    /**
     * 查询用户的所有信息
     *
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() {

        List<UserInfo> users = iUserService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList", users);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 添加用户
     *
     * @param userInfo
     * @param response
     * @param request
     */
    @RequestMapping("/save")
    public void saveOne(UserInfo userInfo, HttpServletResponse response, HttpServletRequest request) {
        iUserService.saveOne(userInfo);
        try {
            request.getRequestDispatcher("/user/findAll").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = iUserService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }
}
