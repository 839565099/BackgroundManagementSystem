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
 * 作者：张翼麒
 * Date:2019-7-15
 */
@Service
public class UserServiceImp implements UserService {
    @Resource
    UserMapper mapper;
    @Resource
    PasswordEncoder passwordEncoder;
    /**
     * 通过用户名得到用户对象
     * 创建用户详情对象 返回
     * @param username
     * @return UserDetails：用户详情
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过方法获取sysuser用户对象
        SysUser sysUser= mapper.findByUsername(username);
        if (sysUser!=null){
            /**
             * 临时创建的假的权限数据 任何角色都可以随便操作
             */
            //创建User构造器第三个参数 --存放用户角色的集合
          //  Collection<GrantedAuthority> list=new ArrayList<>();
            //创建临时角色
          //  GrantedAuthority j=new SimpleGrantedAuthority("ROLE_USER");
            //存入集合
          //  list.add(j);
            //ls 先创建需要返回的UserDetails  它是接口所以找实现类User User构造器中需要传入三个参数 看源码

            /**
             *真的权限控制 查询出该用户对应的角色
             */
            Collection<GrantedAuthority> list=new ArrayList<>();
            //遍历该用户的角色
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
     * 查询全部用户
     * @return
     */
    @Override
    public List<SysUser> findAll() {
        return  mapper.findAll();
    }

    /**
     * 删除多个用户
     * @param ids
     */
    @Override
    public void delMany(Long[] ids) {
        for (Long id : ids) {
            mapper.delOne(id);
        }
    }

    /**
     * 删除一个用户
     * @param id
     */
    @Override
    public void delOne(Long id) {
        mapper.delOne(id);
    }

    /**
     * 添加一个用户 使用security加盐加密
     * @param user
     */
    @Override
    public void save(SysUser user) {
        String password = user.getPassword();
        //加密
        String JMpassword = passwordEncoder.encode(password);
        user.setPassword(JMpassword);
        mapper.save(user);
    }

    /**
     * 分页
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
     * 查询数据库 判断是否已经存在该username
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
     * 根据id查询
     * @param uid
     * @return
     */
    @Override
    public  SysUser findById(int uid) {
        return  mapper.findById(uid);
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param ids
     */
    @Override
    public void addRoleToUser(int userId, int[] ids) {
        //先清空该用户的所有角色
        mapper.delAllRole(userId);
        //清空完再添加先关系
        if (ids!=null){
            for (int id : ids) {
                mapper.saveRole(userId,id);
            }
        }
    }
}
