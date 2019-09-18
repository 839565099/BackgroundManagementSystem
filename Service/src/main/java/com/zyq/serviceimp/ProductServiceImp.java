package com.zyq.serviceimp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyq.mapper.ProductMapper;
import com.zyq.pojo.PageBean;
import com.zyq.pojo.Product;
import com.zyq.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作者：张翼麒
 * Date:2019-7-11
 */
@Service
public class ProductServiceImp implements ProductService {
    @Resource
    ProductMapper mapper;

    public List<Product> findAll() {
        return mapper.findAll();
    }

    @Override
    public void save(Product product) {
        mapper.save(product);
    }

    public Product update(Integer id) {
        Product product = mapper.selectOne(id);
        return product;
    }

    @Override
    public void updateAll(Product product) {
        mapper.updateAll(product);
    }

    @Override
    public void delOne(Integer id) {
        mapper.delOne(id);
    }

    @Override
    public void delMany(Integer[] ids) {
        if (ids!=null){
            for (Integer id : ids) {
                mapper.delMany(id);
            }
        }
    }

    @Override
    public PageBean<Product> findByPage(Integer currPage, Integer pageSize) {
        PageBean<Product> pageBean=new PageBean<>();
//        当前页--页面传参
//        private Integer currPage;
          pageBean.setCurrPage(currPage);
//        每页条数--页面传参
//        private Integer pageSize;
          pageBean.setPageSize(pageSize);
//        总条数--数据库
//        private Long totalCount;
          Long totalCount=mapper.findByTotalCount();
          pageBean.setTotalCount(totalCount);
//        总页数--计算-Math.ceil(totalCount*1.0/pageSize)
//        private Integer totalPage;
          pageBean.setTotalPage((int) Math.ceil(totalCount*1.0/pageSize));
//        当前数据--数据库
//        private List<T> list;
        //每页展示5条
        //第一页：1-5
        //       6-10
        //       11-15
        //第n页   5n-4  --- 5n
        //        5n-(5-1)---5n

        //所以
        Integer start=pageSize*currPage-(pageSize-1);
        Integer end=pageSize*currPage;
        List<Product> productList=mapper.findBypage(start,end);
        pageBean.setList(productList);
        return pageBean;
    }

    /**
     * 测试分页插件
     * @param currPage
     * @param pageSize
     */
    @Override
    public void TestByHelper(Integer currPage, Integer pageSize) {
        //分页参数初始化
        PageHelper.startPage(currPage,pageSize);
        //查询全部
        List<Product> all = mapper.findAll();
        //封装了更为强大的pageBean
        PageInfo<Product> pageInfo=new PageInfo<>(all);//可以在构造其中再传入最多显示多少页 其他隐藏
        System.out.println("当前页："+pageInfo.getPageNum());
        System.out.println("每页条数："+pageInfo.getPageSize());
        System.out.println("总页数 ："+pageInfo.getPages());
        System.out.println("总条数："+pageInfo.getTotal());
        System.out.println("当前页数据数："+pageInfo.getList().size());
        System.out.println("上一页页码："+pageInfo.getPrePage());
        System.out.println("下一页页码："+pageInfo.getNextPage());
        System.out.println("是否是第一页："+pageInfo.isIsFirstPage());
        System.out.println("是否是最后一页："+pageInfo.isIsLastPage());
        System.out.println("页面显示的第一个页码 ："+pageInfo.getNavigateFirstPage());
        System.out.println("页面显示最后一个页码："+pageInfo.getNavigateLastPage());

    }

    @Override
    public PageInfo<Product> findByHelper(Integer currPage, Integer pageSize) {
        PageHelper.startPage(currPage,pageSize);
        List<Product> all = mapper.findAll();
        PageInfo<Product> pageInfo=new PageInfo<>(all,2);
        return pageInfo;
    }
}
