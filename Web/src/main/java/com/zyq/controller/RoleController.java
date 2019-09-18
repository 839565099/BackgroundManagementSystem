package com.zyq.controller;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.Permission;
import com.zyq.pojo.Role;
import com.zyq.service.PermissionService;
import com.zyq.service.RoleService;
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
@RequestMapping("/role")
@Secured({"ROLE_ADMIN"})
public class RoleController {
    @Resource
    RoleService service;
    @Resource
    PermissionService permissionService;

    /**
     * 分页
     * @param currPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(value = "currPage",required =false,defaultValue ="1")int currPage, @RequestParam(value = "pageSize",required = false,defaultValue = "2")int pageSize ){
       PageInfo<Role> pageInfo= service.findByHelper(currPage,pageSize);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    /**
     * 添加角色
     */
    @RequestMapping("/save")
    public String save(Role role){
        service.save(role);
        return "redirect:/role/findAll";
    }

    /**
     * 给角色添加权限
     */
    @RequestMapping("/addpermissionToRole")
    public ModelAndView addpermissionToRole(int roleId){
        //查询全部权限回显
        List<Permission> permissionList=permissionService.findAll();
        //回显该角色已有的权限 查询
        Role role=service.findById(roleId);
        //把role里面的权限遍历 并得到权限id 看看这个角色有什么权限
        StringBuffer stringBuffer=new StringBuffer();
        for (Permission permission : role.getPermissionList()) {
            stringBuffer.append(".");
            stringBuffer.append(permission.getId());
            stringBuffer.append(".");
        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.addObject("str",stringBuffer.toString());
        modelAndView.addObject("roleId",role.getId());
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    /**
     * 勾选好需要添加的权限后 保存操作
     */
    @RequestMapping("/addpermission")
    public String addpermission(int roleId,int[] ids){
        service.addpermission(roleId,ids);
        return "redirect:/role/findAll";
    }


}
