package com.zyq.service;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 作者：张翼麒
 * Date:2019-7-15
 * springSecurity安全框架
 */

public interface UserService extends UserDetailsService {

    List<SysUser> findAll();

    void delMany(Long[] ids);

    void delOne(Long id);

    void save(SysUser user);

    PageInfo<SysUser> findByHelper(int currPage, int pageSize);

    boolean isUniqueUsername(String username);

    SysUser findById(int uid);

    void addRoleToUser(int userId, int[] ids);
}
