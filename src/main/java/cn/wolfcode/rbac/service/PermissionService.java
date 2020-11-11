package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.common.PageResult;
import cn.wolfcode.rbac.common.QueryObject;
import cn.wolfcode.rbac.domain.Permission;

import java.util.List;

public interface PermissionService {
    PageResult<List<Permission>> listAll(QueryObject qo);

    void deleteById(Long id);

    List<Permission> listNoPage();
}
