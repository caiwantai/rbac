package cn.wolfcode.rbac.common;

import lombok.Data;

@Data
public class PageResult<T> {
    private T data;
    private Integer totalPage;
    private Integer currentPage;
}
