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
 * ���ߣ�������
 * Date:2019-7-16
 */
@Service
public class RoleServiceImp implements RoleService {
    @Resource
    RoleMapper mapper;

    /**
     * ��ҳ��ѯ
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
     * ��ӽ�ɫ
     * @param role
     */
    @Override
    public void save(Role role) {
        mapper.save(role);
    }

    /**
     * ��ѯȫ����ɫ
     * @return
     */
    @Override
    public List<Role> findAll() {
        return mapper.findAll();
    }

    /**
     * ����id��ѯ��ɫ
     * @param roleId
     * @return
     */
    @Override
    public Role findById(int roleId) {
        Role byId = mapper.findByIdUI(roleId);
        return byId;
    }

    //����Ȩ��
    @Override
    public void addpermission(int roleId, int[] ids) {
        //������ý�ɫ�Ѿ��е�Ȩ��
        mapper.delAllPermission(roleId);
        if (ids!=null){
            for (int id : ids) {
                mapper.savePermission(roleId,id);
            }
        }
    }
}
