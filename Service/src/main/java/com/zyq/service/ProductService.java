package com.zyq.service;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.PageBean;
import com.zyq.pojo.Product;

import java.util.List;

/**
 * 作者：张翼麒
 * Date:2019-7-11
 */
public interface ProductService {
    /**
     *查询全部产品
     * @return
     */
   public List<Product> findAll();

    /**
     * 添加产品
     * @param product
     */
   public void save(Product product);

    /**
     * 修改产品信息
     * @param id
     */
   public  Product update(Integer id);

    /**
     * 更新产品
     * @param product
     */
    public void updateAll(Product product);

    /**
     * 删除一个产品
     * @param id
     */
    void delOne(Integer id);

    /**
     * 刪除多个产品
     * @param ids
     */
    void delMany(Integer[] ids);

    /**
     * 手动分页
     * @param currPage
     * @param pageSize
     * @return
     */
    PageBean<Product> findByPage(Integer currPage, Integer pageSize);


    /**
     * 测试分页插件
     */
     void TestByHelper(Integer currPage, Integer pageSize);

    /**
     * 分页查询
     */
    PageInfo<Product> findByHelper(Integer currPage, Integer pageSize);
}
