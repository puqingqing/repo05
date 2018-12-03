package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService iOrdersService;

   /* @RequestMapping("/findAll")
    public String findAll(Model model){

     List<Orders> orders=iOrdersService.findAll();
        model.addAttribute("ordersList",orders);
        return  "orders-list";
    } */

    /**
     * 查询订单的全部信息
     * @param page
     * @param size
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name="page",required = true,defaultValue = "1")int page,
                          @RequestParam(name="size",required = true,defaultValue = "4")int size, Model model){

     List<Orders> orders=iOrdersService.findAll(page,size);
      //pagebean的封装
       PageInfo pages=new PageInfo(orders);
       model.addAttribute("pages",pages);
        return  "orders-page-list";
    }

    /**
     * 查询订单的详细信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findById")
    public String findById(String id,Model model){
      Orders order= iOrdersService.findById(id);
       model.addAttribute("orders",order);
        return "orders-show";
    }
}
