package com.zyq.controller;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.Role;
import com.zyq.pojo.SysUser;
import com.zyq.service.RoleService;
import com.zyq.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作者：张翼麒
 * Date:2019-7-15
 */
@Controller
@RequestMapping("/user")
@Secured({"ROLE_ADMIN"})
public class UserController {
    @Resource
    UserService service;

    @Resource
    RoleService roleService;

    /**
     * 查询全部用户
     * @return
     */
    @RequestMapping("/findAll-1")
    public ModelAndView findAll_1(){
        List<SysUser> users =service.findAll();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("userlist",users);
        return modelAndView;
    }

    /**
     * 删除多个
     * @param ids
     * @return
     */
    @RequestMapping("/delMany")
    public String delMany(Long[] ids){
        service.delMany(ids);
        return "redirect:/user/findAll";
    }
    /**
     * 删除单个
     */
    @RequestMapping("/delOne")
    public String delOne(Long id){
        service.delOne(id);
        return "redirect:/user/findAll";
    }

    /**
     * 添加用户
     */
    @RequestMapping("/save")
    public String save(SysUser user){
        service.save(user);
        return "redirect:/user/findAll";
    }

    /**
     * 分页插件
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(value = "currPage",required =false,defaultValue ="1")int currPage,@RequestParam(value = "pageSize",required = false,defaultValue = "4")int pageSize ){
        PageInfo<SysUser> pageInfo =service.findByHelper(currPage,pageSize);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }

    /**
     * 接收ajax请求 判断用户名是否唯一
     *  @ResponseBody：将结果集转化为json以流的形式返回
     * @param username
     * @return
     */
    @RequestMapping("/isUniqueUsername")
    @ResponseBody
    public String isUniqueUsername(String username){
        boolean b= service.isUniqueUsername(username);
        return String.valueOf(b);
    }

    /**
     * 用户详情
     */
    @RequestMapping("/detils")
    public ModelAndView detils(int uid){
        SysUser sysUser= service.findById(uid);
        ModelAndView m=new ModelAndView();
        m.setViewName("user-show");
        m.addObject("sysUser",sysUser);
        return m;
    }

    /**
     * 添加用户角色数据回显
     */
    @RequestMapping("/addRoleToUserUI")
    public ModelAndView addRoleToUserUI(int uid){
        //查询全部的角色
        List<Role> roleList = roleService.findAll();
        //查询用户已有的角色
        SysUser user = service.findById(uid);
        //把角色id 拼接成字符串str=.1..2..3..
        List<Role> roles = user.getRoleList();
        StringBuffer stringBuffer=new StringBuffer();
        for (Role role : roles) {
            stringBuffer.append(".");
            stringBuffer.append(role.getId());
            stringBuffer.append(".");
        }


        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("roleList",roleList);
        modelAndView.addObject("str",stringBuffer.toString());
        modelAndView.setViewName("user-role-add");
        //还需要存一个用户id 当后面添加角色时候需要一个用户id
        modelAndView.addObject("userid",user.getId());
        return modelAndView;
    }

    /**
     * 添加角色给用户
     *参数需要：勾选的角色id数组 用户id
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(int userId,int[] ids){
        service.addRoleToUser(userId,ids);
        return "redirect:/user/findAll";

    }

}
