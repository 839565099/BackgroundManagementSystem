package com.zyq.serviceimp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyq.mapper.UserMapper;
import com.zyq.pojo.Role;
import com.zyq.pojo.SysUser;
import com.zyq.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ���ߣ�������
 * Date:2019-7-15
 */
@Service
public class UserServiceImp implements UserService {
    @Resource
    UserMapper mapper;
    @Resource
    PasswordEncoder passwordEncoder;
    /**
     * ͨ���û����õ��û�����
     * �����û�������� ����
     * @param username
     * @return UserDetails���û�����
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //ͨ��������ȡsysuser�û�����
        SysUser sysUser= mapper.findByUsername(username);
        if (sysUser!=null){
            /**
             * ��ʱ�����ļٵ�Ȩ������ �κν�ɫ������������
             */
            //����User���������������� --����û���ɫ�ļ���
          //  Collection<GrantedAuthority> list=new ArrayList<>();
            //������ʱ��ɫ
          //  GrantedAuthority j=new SimpleGrantedAuthority("ROLE_USER");
            //���뼯��
          //  list.add(j);
            //ls �ȴ�����Ҫ���ص�UserDetails  ���ǽӿ�������ʵ����User User����������Ҫ������������ ��Դ��

            /**
             *���Ȩ�޿��� ��ѯ�����û���Ӧ�Ľ�ɫ
             */
            Collection<GrantedAuthority> list=new ArrayList<>();
            //�������û��Ľ�ɫ
            for (Role role : sysUser.getRoleList()) {
                GrantedAuthority j=new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
                list.add(j);
            }

            UserDetails userDetails=new User(sysUser.getUsername(),sysUser.getPassword(),list);
            return userDetails;
        }else{
            return null;
        }

    }

    /**
     * ��ѯȫ���û�
     * @return
     */
    @Override
    public List<SysUser> findAll() {
        return  mapper.findAll();
    }

    /**
     * ɾ������û�
     * @param ids
     */
    @Override
    public void delMany(Long[] ids) {
        for (Long id : ids) {
            mapper.delOne(id);
        }
    }

    /**
     * ɾ��һ���û�
     * @param id
     */
    @Override
    public void delOne(Long id) {
        mapper.delOne(id);
    }

    /**
     * ���һ���û� ʹ��security���μ���
     * @param user
     */
    @Override
    public void save(SysUser user) {
        String password = user.getPassword();
        //����
        String JMpassword = passwordEncoder.encode(password);
        user.setPassword(JMpassword);
        mapper.save(user);
    }

    /**
     * ��ҳ
     * @param currPage
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SysUser> findByHelper(int currPage, int pageSize) {
        PageHelper.startPage(currPage,pageSize);
        List<SysUser> all=mapper.findAll();
        PageInfo<SysUser> pageInfo=new PageInfo<>(all,2);
        return pageInfo;
    }

    /**
     * ��ѯ���ݿ� �ж��Ƿ��Ѿ����ڸ�username
     * @param username
     * @return
     */
    @Override
    public boolean isUniqueUsername(String username) {
        SysUser sysUser=mapper.findByIsUnique(username);
        if (sysUser==null){
            return true;
        }else{
            return false;
        }

    }

    /**
     * ����id��ѯ
     * @param uid
     * @return
     */
    @Override
    public  SysUser findById(int uid) {
        return  mapper.findById(uid);
    }

    /**
     * ���û���ӽ�ɫ
     * @param userId
     * @param ids
     */
    @Override
    public void addRoleToUser(int userId, int[] ids) {
        //����ո��û������н�ɫ
        mapper.delAllRole(userId);
        //�����������ȹ�ϵ
        if (ids!=null){
            for (int id : ids) {
                mapper.saveRole(userId,id);
            }
        }
    }
}
