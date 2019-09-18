package com.zyq.mapper;

import com.zyq.pojo.Role;

import java.util.List;

/**
 * ×÷Õß£ºÕÅÒí÷è
 * Date:2019-7-16
 */
public interface RoleMapper {

    List<Role> findAll();

    void save(Role role);

    List<Role> findById(int roleid);

    Role findByIdUI(int roleId);

    void delAllPermission(int roleId);

    void savePermission(int roleId, int id);
}
