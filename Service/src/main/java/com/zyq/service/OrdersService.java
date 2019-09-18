package com.zyq.service;

import com.zyq.pojo.Orders;

import java.util.List;

/**
 * 作者：张翼麒
 * Date:2019-7-13
 */
public interface OrdersService {
   //查询全部订单
    List<Orders> findAll();
   //添加订单
    void save(Orders orders);
    //删除单条订单
    void delOne(Integer id);
   //删除多条记录
    void delMany(Integer[] ids);
}
