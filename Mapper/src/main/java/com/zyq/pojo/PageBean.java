package com.zyq.pojo;

import java.util.List;

/**
 * ���ߣ�������
 * Date:2019-7-14
 * ��ҳ����
 */
public class PageBean<T> {
    //��ǰҳ--ҳ�洫��
    private Integer currPage;
    //ÿҳ����--ҳ�洫��
    private Integer pageSize;
    //������--���ݿ�
    private Long totalCount;
    //��ҳ��--����-Math.ceil(totalCount*1.0/pageSize)
    private Integer totalPage;
    //��ǰ����--���ݿ�
    private List<T> list;

    public PageBean() {
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
