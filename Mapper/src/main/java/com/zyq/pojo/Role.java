package com.zyq.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张翼麒
 * Date:2019-7-16
 */
public class Role {
    private Long id;
    private String roleName;
    private String roleDesc;
//一个角色有多个用户
    private List<SysUser> sysUserList=new ArrayList<>();
    //一个角色有多个权限
    private List<Permission> permissionList =new ArrayList<>();

    public Role() {

    }

    public List<SysUser> getSysUserList() {
        return sysUserList;
    }

    public void setSysUserList(List<SysUser> sysUserList) {
        this.sysUserList = sysUserList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
