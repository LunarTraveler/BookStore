package com.xcu.pojo;

import java.util.List;

public class Page <T> {
    // 默认的分页每页的页面展示数据页数
    public static final Integer PAGE_SIZE = 4;
    // 当前的页码
    private Integer pageNo;
    // 总页码
    private Integer pageTotal;
    // 总记录数
    private Integer pageTotalCount;
    // 每页显示数量
    private Integer pageSize = PAGE_SIZE;
    // 每页显示数据集合
    private List<T> items;
    // 前后台显示分页的区别
    private String url;

    public Integer getPageNo() {
        return pageNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPageNo(Integer pageNo) {
        // 数据边缘有效性
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
