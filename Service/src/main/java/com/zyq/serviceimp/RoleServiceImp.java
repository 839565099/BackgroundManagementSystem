package com.zyq.serviceimp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyq.mapper.RoleMapper;
import com.zyq.pojo.Role;
import com.zyq.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作者：张翼麒
 * Date:2019-7-16
 */
@Service
public class RoleServiceImp implements RoleService {
    @Resource
    RoleMapper mapper;

    /**
     * 分页查询
     * @param currPage
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Role> findByHelper(int currPage, int pageSize) {
        PageHelper.startPage(currPage,pageSize);
        List<Role> list=mapper.findAll();
        PageInfo<Role> pageInfo=new PageInfo<>(list,2);
        return pageInfo;
    }

    /**
     * 添加角色
     * @param role
     */
    @Override
    public void save(Role role) {
        mapper.save(role);
    }

    /**
     * 查询全部角色
     * @return
     */
    @Override
    public List<Role> findAll() {
        return mapper.findAll();
    }

    /**
     * 根据id查询角色
     * @param roleId
     * @return
     */
    @Override
    public Role findById(int roleId) {
        Role byId = mapper.findByIdUI(roleId);
        return byId;
    }

    //保存权限
    @Override
    public void addpermission(int roleId, int[] ids) {
        //先清除该角色已经有的权限
        mapper.delAllPermission(roleId);
        if (ids!=null){
            for (int id : ids) {
                mapper.savePermission(roleId,id);
            }
        }
    }
}
