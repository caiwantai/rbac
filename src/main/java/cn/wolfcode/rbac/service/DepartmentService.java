package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.common.PageResult;
import cn.wolfcode.rbac.common.QueryObject;
import cn.wolfcode.rbac.domain.Department;

import java.util.List;

public interface DepartmentService {
    PageResult<List<Department>> selectByCondition(QueryObject qo);

    Department selectById(  Long id);

    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    int updateByPrimaryKey(Department record);
}
