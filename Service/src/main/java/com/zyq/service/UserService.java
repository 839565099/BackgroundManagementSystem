package com.zyq.service;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * ���ߣ�������
 * Date:2019-7-15
 * springSecurity��ȫ���
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
