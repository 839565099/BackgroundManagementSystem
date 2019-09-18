package com.zyq.service;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.PageBean;
import com.zyq.pojo.Product;

import java.util.List;

/**
 * ���ߣ�������
 * Date:2019-7-11
 */
public interface ProductService {
    /**
     *��ѯȫ����Ʒ
     * @return
     */
   public List<Product> findAll();

    /**
     * ��Ӳ�Ʒ
     * @param product
     */
   public void save(Product product);

    /**
     * �޸Ĳ�Ʒ��Ϣ
     * @param id
     */
   public  Product update(Integer id);

    /**
     * ���²�Ʒ
     * @param product
     */
    public void updateAll(Product product);

    /**
     * ɾ��һ����Ʒ
     * @param id
     */
    void delOne(Integer id);

    /**
     * �h�������Ʒ
     * @param ids
     */
    void delMany(Integer[] ids);

    /**
     * �ֶ���ҳ
     * @param currPage
     * @param pageSize
     * @return
     */
    PageBean<Product> findByPage(Integer currPage, Integer pageSize);


    /**
     * ���Է�ҳ���
     */
     void TestByHelper(Integer currPage, Integer pageSize);

    /**
     * ��ҳ��ѯ
     */
    PageInfo<Product> findByHelper(Integer currPage, Integer pageSize);
}
