package com.zyq.controller;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.PageBean;
import com.zyq.pojo.Product;
import com.zyq.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作者：张翼麒
 * Date:2019-7-11
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    ProductService productService;

    /**
     * 查询全部的产品信息
     *
     * @return
     */
    @RequestMapping("/findAll_1")
    public ModelAndView findAll_1() {
        List<Product> productList = productService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    /**
     * 添加产品
     *
     * @param product
     * @return
     */
    @RequestMapping("/save")
    public String save(Product product) {
        productService.save(product);
        return "redirect:/product/findAll";
    }

    /**
     * 根据id查询商品信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/updateById")
    public ModelAndView update(Integer id) {
        Product update = productService.update(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product-update");
        modelAndView.addObject("product", update);
        return modelAndView;
    }

    /**
     * 更新产品信息
     *
     * @param product
     * @return
     */
    @RequestMapping("/update")
    public String update(Product product) {
        productService.updateAll(product);
        return "redirect:/product/findAll";
    }

    /**
     * 删除一个产品
     *
     * @param id
     * @return
     */
    @RequestMapping("/delOne")
    public String delOne(Integer id) {
        productService.delOne(id);
        return "redirect:/product/findAll";
    }

    /**
     * 删除多个产品
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delMany")
    public String delMany(Integer[] ids) {
        productService.delMany(ids);
        return "redirect:/product/findAll";
    }

    /**
     * 手动分页
     * 查询全部的方法 使用@RequestParam可以判断当没有传参数时候(首次点击左边菜单栏查询)默认查询第一页 每页5条
     * @param currPage 当前页
     * @param pageSize 每页的条数
     * @return
     */
    @RequestMapping("/findAll_2")
    public ModelAndView findAll_2(@RequestParam(value = "currPage",required = false,defaultValue ="1") Integer currPage , @RequestParam(value = "pageSize",required = false,defaultValue ="5") Integer pageSize ) {
        PageBean<Product> productPageBean=productService.findByPage(currPage,pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageBean", productPageBean);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    /**
     * 分页插件查询
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(value = "currPage",required = false,defaultValue ="1") Integer currPage , @RequestParam(value = "pageSize",required = false,defaultValue ="5") Integer pageSize ) {
        PageInfo<Product> pageInfo = productService.findByHelper(currPage, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

}
