package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.mapper.PermissionMapper;
import cn.wolfcode.rbac.mapper.RoleMapper;
import cn.wolfcode.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Role> listAll() {
        return roleMapper.listAll();
    }

    @Override
    public Role selectById(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Role record,String permissionIds) {
        roleMapper.insert(record);
        if (Objects.nonNull(permissionIds)&& permissionIds.length()>0){
            String[] split = permissionIds.split(",");
            for (String s : split) {
                permissionMapper.insertByRoleId(record.getId(),Long.parseLong(s));
            }
        }
    }

    @Override
    public void updateByPrimaryKey(Role record,String permissionIds) {
        permissionMapper.deleteByRoleId(record.getId());
        roleMapper.updateByPrimaryKey(record);
        if (Objects.nonNull(permissionIds)&& permissionIds.length()>0){
            String[] split = permissionIds.split(",");
            for (String s : split) {
                permissionMapper.insertByRoleId(record.getId(),Long.parseLong(s));
            }
        }
    }

}
