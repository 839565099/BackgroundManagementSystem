package com.zyq.serviceimp;

import com.zyq.mapper.OrdersMapper;
import com.zyq.pojo.Orders;
import com.zyq.service.OrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ×÷Õß£ºÕÅÒí÷è
 * Date:2019-7-13
 */
@Service
public class OrdersServiceImp implements OrdersService {
    @Resource
    OrdersMapper mapper;
    @Override
    public List<Orders> findAll() {
        List<Orders> all = mapper.findAll();
        return all;
    }

    @Override
    public void save(Orders orders) {
        mapper.save(orders);
    }

    @Override
    public void delOne(Integer id) {
        mapper.delOne(id);
    }

    @Override
    public void delMany(Integer[] ids) {
        for (Integer id : ids) {
            mapper.delOne(id);
        }
    }
}
