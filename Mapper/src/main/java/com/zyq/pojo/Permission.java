package com.zyq.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ߣ�������
 * Date:2019-7-16
 */
public class Permission {
    private Long id;
    private String permissionName;
    private String url;
    private Long pid;
    //һ��Ȩ���ж����ɫ
    private List<Role> roleList=new ArrayList<>();

    public Permission() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
