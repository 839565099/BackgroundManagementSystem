package com.zyq.service;

import com.zyq.pojo.Orders;

import java.util.List;

/**
 * ���ߣ�������
 * Date:2019-7-13
 */
public interface OrdersService {
   //��ѯȫ������
    List<Orders> findAll();
   //��Ӷ���
    void save(Orders orders);
    //ɾ����������
    void delOne(Integer id);
   //ɾ��������¼
    void delMany(Integer[] ids);
}
