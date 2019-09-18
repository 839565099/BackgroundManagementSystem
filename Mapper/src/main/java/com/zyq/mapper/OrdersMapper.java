package com.zyq.mapper;

import com.zyq.pojo.Orders;

import java.util.List;

/**
 * ×÷Õß£ºÕÅÒí÷è
 * Date:2019-7-13
 */
public interface OrdersMapper {
    List<Orders> findAll();

    void save(Orders orders);

    void delOne(Integer id);
}
