package com.itheima.ssm.controller;

import com.itheima.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Product> products = iProductService.findAll();
        mv.addObject("productList",products);
        mv.setViewName("product_list");
        return mv;
    }

    @RequestMapping("/save")
    public void  saveOne(Product product,HttpServletRequest request,HttpServletResponse response) throws Exception {
         iProductService.saveOne(product);
        System.out.println("ssss");
        request.getRequestDispatcher("findAll").forward(request,response);
    }

}
