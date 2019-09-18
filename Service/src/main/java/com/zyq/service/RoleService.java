package com.zyq.service;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.Role;

import java.util.List;

/**
 * ×÷Õß£ºÕÅÒí÷è
 * Date:2019-7-16
 */
public interface RoleService {
    PageInfo<Role> findByHelper(int currPage, int pageSize);

    void save(Role role);

    List<Role> findAll();

    Role findById(int roleId);

    void addpermission(int roleId, int[] ids);
}
