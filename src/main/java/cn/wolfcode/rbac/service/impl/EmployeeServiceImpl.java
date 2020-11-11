package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.common.PageResult;
import cn.wolfcode.rbac.common.QueryObject;
import cn.wolfcode.rbac.common.UserPermission;
import cn.wolfcode.rbac.common.utils.SessionUtil;
import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.mapper.DepartmentMapper;
import cn.wolfcode.rbac.mapper.EmployeeMapper;
import cn.wolfcode.rbac.mapper.PermissionMapper;
import cn.wolfcode.rbac.service.EmployeeService;
import cn.wolfcode.rbac.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Map<String, Object> listAllCondition(QueryObject qo) {
        Map<String, Object> map = new HashMap<>();
        List<Department> departments = departmentMapper.listDepartment();
        map.put("departments", departments);
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());

        List<Employee> employees = employeeMapper.listAllCondition(qo);
        PageInfo<Employee> pageInfo = new PageInfo<>(employees);
        // 数据封装
        PageResult<List<Employee>> pageResult = new PageResult<>();
        pageResult.setData(employees);
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setCurrentPage(pageInfo.getPageNum());
        map.put("pageResult", pageResult);
        return map;
    }

    @Override
    public Employee selectById(Long id) {
        Employee employee = employeeMapper.selectById(id);
        List<Role> roles = employeeMapper.selectRoleByEmId(id);
        employee.setRoles(roles);
        return employee;
    }

    @Transactional
    @Override
    public void deleteByPrimaryKey(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
        employeeMapper.deleteRolesByEmId(id);
    }

    @Transactional
    @Override
    public void updateByPrimaryKey(Employee employee,String roleIds) {
        employeeMapper.updateByPrimaryKey(employee);
        employeeMapper.deleteRolesByEmId(employee.getId());
        if (Objects.nonNull(roleIds) && roleIds.length()>0){
            String[] split = roleIds.split(",");
            for (String s : split) {
                employeeMapper.insertEmployeeAndRoles(employee.getId(), Long.parseLong(s));
            }
        }
    }

    @Transactional
    @Override
    public void insert(Employee employee,String roleIds) {
        employeeMapper.insert(employee);
        if (Objects.nonNull(roleIds) && roleIds.length()>0){
            String[] split = roleIds.split(",");
            for (String s : split) {
                employeeMapper.insertEmployeeAndRoles(employee.getId(), Long.parseLong(s));
            }
        }
    }

    @Override
    public Map<String, Object>  listDepartmentAndRole() {
        Map<String, Object> map = new HashMap<>();
        map.put("departments", departmentMapper.listDepartment());
        map.put("roles", roleService.listAll());
        return map;
    }

    @Override
    public boolean checkNameAndPassword(String name, String password) {
        Employee employee = employeeMapper.selectByName(name);
        if (Objects.isNull(employee)){
            return false;
        }
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (employee.getPassword().equals(password)){
            List<Permission> permissions = permissionMapper.getPermissionByEmId(employee.getId());
            UserPermission userPermission = new UserPermission();
            BeanUtils.copyProperties(employee,userPermission);
            userPermission.setPermissions(permissions);
            SessionUtil.setCurrentSessionAttribute(userPermission);
            return true;
        }
        return false;
    }

}
