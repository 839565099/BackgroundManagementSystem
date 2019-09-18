package com.zyq.mapper;

import com.zyq.pojo.SysUser;

import java.util.List;

/**
 * ×÷Õß£ºÕÅÒí÷è
 * Date:2019-7-15
 */
public interface UserMapper {
    SysUser findByUsername(String username);

    List<SysUser> findAll();

    void delOne(Long id);

    void save(SysUser user);

    SysUser findByIsUnique(String username);

    SysUser findById(int uid);

    void delAllRole(int userId);

    void saveRole(int userId, int ids);
}
