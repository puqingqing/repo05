package com.itheima.ssm.controller;


import com.itheima.domain.Syslog;
import com.itheima.ssm.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SyslogController {

    @Autowired
    private ISyslogService syslogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Syslog> syslogs = syslogService.findAll();
        mv.addObject("sysLogs", syslogs);
        mv.setViewName("syslog-list");
        return mv;

    }
}
