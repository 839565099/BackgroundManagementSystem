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
 * ���ߣ�������
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
//        ��ǰҳ--ҳ�洫��
//        private Integer currPage;
          pageBean.setCurrPage(currPage);
//        ÿҳ����--ҳ�洫��
//        private Integer pageSize;
          pageBean.setPageSize(pageSize);
//        ������--���ݿ�
//        private Long totalCount;
          Long totalCount=mapper.findByTotalCount();
          pageBean.setTotalCount(totalCount);
//        ��ҳ��--����-Math.ceil(totalCount*1.0/pageSize)
//        private Integer totalPage;
          pageBean.setTotalPage((int) Math.ceil(totalCount*1.0/pageSize));
//        ��ǰ����--���ݿ�
//        private List<T> list;
        //ÿҳչʾ5��
        //��һҳ��1-5
        //       6-10
        //       11-15
        //��nҳ   5n-4  --- 5n
        //        5n-(5-1)---5n

        //����
        Integer start=pageSize*currPage-(pageSize-1);
        Integer end=pageSize*currPage;
        List<Product> productList=mapper.findBypage(start,end);
        pageBean.setList(productList);
        return pageBean;
    }

    /**
     * ���Է�ҳ���
     * @param currPage
     * @param pageSize
     */
    @Override
    public void TestByHelper(Integer currPage, Integer pageSize) {
        //��ҳ������ʼ��
        PageHelper.startPage(currPage,pageSize);
        //��ѯȫ��
        List<Product> all = mapper.findAll();
        //��װ�˸�Ϊǿ���pageBean
        PageInfo<Product> pageInfo=new PageInfo<>(all);//�����ڹ��������ٴ��������ʾ����ҳ ��������
        System.out.println("��ǰҳ��"+pageInfo.getPageNum());
        System.out.println("ÿҳ������"+pageInfo.getPageSize());
        System.out.println("��ҳ�� ��"+pageInfo.getPages());
        System.out.println("��������"+pageInfo.getTotal());
        System.out.println("��ǰҳ��������"+pageInfo.getList().size());
        System.out.println("��һҳҳ�룺"+pageInfo.getPrePage());
        System.out.println("��һҳҳ�룺"+pageInfo.getNextPage());
        System.out.println("�Ƿ��ǵ�һҳ��"+pageInfo.isIsFirstPage());
        System.out.println("�Ƿ������һҳ��"+pageInfo.isIsLastPage());
        System.out.println("ҳ����ʾ�ĵ�һ��ҳ�� ��"+pageInfo.getNavigateFirstPage());
        System.out.println("ҳ����ʾ���һ��ҳ�룺"+pageInfo.getNavigateLastPage());

    }

    @Override
    public PageInfo<Product> findByHelper(Integer currPage, Integer pageSize) {
        PageHelper.startPage(currPage,pageSize);
        List<Product> all = mapper.findAll();
        PageInfo<Product> pageInfo=new PageInfo<>(all,2);
        return pageInfo;
    }
}
