package com.zyq.service;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.Permission;

import java.util.List;

/**
 * ×÷Õß£ºÕÅÒí÷è
 * Date:2019-7-16
 */
public interface PermissionService {
    PageInfo<Permission> findByHelper(int currPage, int pageSize);

    List<Permission> saveUI();

    void save(Permission permission);

    List<Permission> findAll();
}
