package com.zyq.mapper;

import com.zyq.pojo.Permission;

import java.util.List;

/**
 * ×÷Õß£ºÕÅÒí÷è
 * Date:2019-7-16
 */
public interface PermissionMapper {
    List<Permission> findAll();

    List<Permission> saveUI();

    void save(Permission permission);

    List<Permission> findById(int id);
}
