package com.itheima.ssm.controller;

import com.itheima.domain.Syslog;
import com.itheima.ssm.service.ISyslogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;


@Component
@Aspect
public class LogAop {

    private Date startTime;//访问时间
    private Class executionClass;// 访问的类
    private Method method01;//方法对象
    private String url;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISyslogService iSyslogService;

    @Before("execution(* com.itheima.ssm.controller..*.*(..) )")
    public void doBefore(JoinPoint jp) throws Exception {
        //获取访问时间
        startTime = new Date();
        //获取访问的类
        executionClass = jp.getTarget().getClass();
        //获取访问的方法名
        String methodname = jp.getSignature().getName();
        //获取方法参数
        Object[] args = jp.getArgs();
        Class[] grs = new Class[args.length];
        if (args == null || args.length <= 0) {

            method01= executionClass.getMethod(methodname);
        } else {

            for (int i = 0; i < args.length; i++) {
                grs[i] = args[i].getClass();
            }
            method01 = executionClass.getMethod(methodname, grs);
        }

    }



    @After("execution(* com.itheima.ssm.controller.*.*(..) )")
    public void doAfter(JoinPoint jp) {
        String[] classvalue = null;
        String[] methodvalue = null;

        //获取url路径
        if (executionClass != null && method01 != null && executionClass != LogAop.class) {
            RequestMapping classannotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classannotation != null) {

                classvalue = classannotation.value();

                RequestMapping methodannotation = method01.getAnnotation(RequestMapping.class);

                if (methodannotation != null) {
                    methodvalue = methodannotation.value();
                    url = classvalue[0] + methodvalue[0];
                }

            }

            Syslog syslog = new Syslog();



            String ip = request.getRemoteAddr();//获取iP

            SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");

            //获取操作者
            String username = ((User)
                    (context.getAuthentication().getPrincipal())).getUsername();




            String method="[类名：]"+executionClass.getName()+"[方法名]"+method01.getName();


            syslog.setVisitTime(startTime);
            syslog.setUsername(username);//获取操作者
            syslog.setIp(ip);//设置ip
            syslog.setUrl(url); //设置url
            syslog.setMethod(method);//设置方法
            syslog.setExecutionTime(new Date().getTime() - startTime.getTime());//设置执行时长

            //保存方法
             iSyslogService.saveOne(syslog);
        }


    }
}
