package com.pl.model;

/**
 * 页面返回数据体
 */
public class PageInfoBO {
    private Object pageInfo;

    private Integer pageCount;

    private Integer pageSize;

    private Integer pageNumb;

    public Object getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(Object pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumb() {
        return pageNumb;
    }

    public void setPageNumb(Integer pageNumb) {
        this.pageNumb = pageNumb;
    }

    @Override
    public String toString() {
        return "PageInfoBO{" +
                "pageInfo=" + pageInfo +
                ", pageCount=" + pageCount +
                ", pagesize=" + pageSize +
                ", pageNumb=" + pageNumb +
                '}';
    }
}
