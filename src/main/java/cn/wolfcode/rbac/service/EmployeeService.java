package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.common.QueryObject;
import cn.wolfcode.rbac.domain.Employee;

import java.util.Map;

public interface EmployeeService {

    Map<String, Object> listAllCondition(QueryObject qo);

    Employee selectById(Long id);

    void   deleteByPrimaryKey(Long id);

    void updateByPrimaryKey(Employee employee,String roleIds);

    void insert(Employee employee,String roleIds);

    Map<String, Object> listDepartmentAndRole();

    boolean checkNameAndPassword(String name,String password);
}
