package cn.wolfcode.rbac.common;

import lombok.Data;

@Data
public class QueryObject {
    private int pageSize = 5;
    private int currentPage = 1;
}
