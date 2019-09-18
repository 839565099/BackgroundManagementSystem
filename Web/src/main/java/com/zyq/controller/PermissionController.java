package com.zyq.controller;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.Permission;
import com.zyq.service.PermissionService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作者：张翼麒
 * Date:2019-7-16
 */
@Controller
@RequestMapping("/permission")
@Secured({"ROLE_ADMIN"})
public class PermissionController {
    @Resource
    PermissionService service;

    /**
     * 分页
     * @param currPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(value = "currPage",required =false,defaultValue ="1")int currPage, @RequestParam(value = "pageSize",required = false,defaultValue = "4")int pageSize ){
        PageInfo<com.zyq.pojo.Permission> pageInfo=service.findByHelper(currPage,pageSize);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("permission-list");
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }

    /**
     * 添加权限数据回显
     */
    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        List<Permission> list=service.saveUI();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("permission-add");
        return modelAndView;
    }
    /**
     * 添加数据
     */
    @RequestMapping("/save")
    public String save(Permission permission){
        service.save(permission);
        return "redirect:/permission/findAll";
    }
}
