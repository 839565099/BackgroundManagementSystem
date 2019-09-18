package com.zyq.controller;

import com.zyq.pojo.Orders;
import com.zyq.pojo.Product;
import com.zyq.service.OrdersService;
import com.zyq.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作者：张翼麒
 * Date:2019-7-13
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Resource
    OrdersService ordersService;
    @Resource
    ProductService productService;
    /**
     * 查询全部订单
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Orders> ordersList = ordersService.findAll();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("ordersList",ordersList);
        modelAndView.setViewName("order-list");
        return modelAndView;
    }
    /**
     * 添加订单页面数据回显
     */
    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        List<Product> all = productService.findAll();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("order-add");
        modelAndView.addObject("plist",all);
        return modelAndView;
    }

    /**
     * 添加订单
     * @param orders
     * @return
     */
    @RequestMapping("/save")
    public String save(Orders orders){
        ordersService.save(orders);
        return "redirect:/orders/findAll";
    }

    /**
     * 删除单条数据
     */
    @RequestMapping("/delOne")
    public String delOne(Integer id){
        ordersService.delOne(id);
        return "redirect:/orders/findAll";
    }

    /**
     * 删除多个订单
     */
    @RequestMapping("/delMany")
    public String delMany(Integer[] ids){
        ordersService.delMany(ids);
        return "redirect:/orders/findAll";
    }

}
