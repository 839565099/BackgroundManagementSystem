package com.zyq.pojo;

import java.util.List;

/**
 * 作者：张翼麒
 * Date:2019-7-14
 * 分页对象
 */
public class PageBean<T> {
    //当前页--页面传参
    private Integer currPage;
    //每页条数--页面传参
    private Integer pageSize;
    //总条数--数据库
    private Long totalCount;
    //总页数--计算-Math.ceil(totalCount*1.0/pageSize)
    private Integer totalPage;
    //当前数据--数据库
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
