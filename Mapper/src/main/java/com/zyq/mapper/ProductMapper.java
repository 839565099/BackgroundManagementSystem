package com.zyq.mapper;

import com.zyq.pojo.Product;

import java.util.List;

/**
 * ×÷Õß£ºÕÅÒí÷è
 * Date:2019-7-11
 */
public interface ProductMapper {

     public List<Product> findAll();

     public void save(Product product);

    public Product selectOne(Integer id);

    public void updateAll(Product product);

    public void delOne(Integer id);

    void delMany(Integer id);

    Long findByTotalCount();

    List<Product> findBypage(Integer start, Integer end);
}
