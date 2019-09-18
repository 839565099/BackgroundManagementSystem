package com.zyq.serviceimp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyq.mapper.PermissionMapper;
import com.zyq.pojo.Permission;
import com.zyq.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ×÷Õß£ºÕÅÒí÷è
 * Date:2019-7-16
 */
@Service
public class PermissionServiceImp implements PermissionService {
    @Resource
    PermissionMapper mapper;
    @Override
    public PageInfo<Permission> findByHelper(int currPage, int pageSize) {
        PageHelper.startPage(currPage,pageSize);
        List<Permission> list =mapper.findAll();
        PageInfo<Permission> pageInfo =new PageInfo<>(list,2);
        return pageInfo;
    }

    @Override
    public List<Permission> saveUI() {
        List<Permission> list=mapper.saveUI();
        return list;
    }

    @Override
    public void save(Permission permission) {
        mapper.save(permission);
    }

    @Override
    public List<Permission> findAll() {

        return mapper.findAll();
    }
}
