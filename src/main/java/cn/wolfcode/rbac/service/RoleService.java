package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> listAll();

    Role selectById(  Long id);

    int deleteByPrimaryKey( Long id);

    void insert(Role record, String permissionIds);

    void updateByPrimaryKey(Role record, String permissionIds);

}
