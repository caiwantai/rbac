package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.common.PageResult;
import cn.wolfcode.rbac.common.QueryObject;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.mapper.PermissionMapper;
import cn.wolfcode.rbac.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public PageResult<List<Permission>> listAll(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Permission> permissions = permissionMapper.listAll();
        PageInfo<Permission> pageInfo = new PageInfo<>(permissions);
        PageResult<List<Permission>> pageResult = new PageResult<>();
        pageResult.setData(permissions);
        pageResult.setCurrentPage(pageInfo.getPageNum());
        pageResult.setTotalPage(pageInfo.getPages());
        return pageResult;
    }

    @Override
    public void deleteById(Long id) {
        permissionMapper.deleteById(id);
    }

    @Override
    public List<Permission> listNoPage() {
        return permissionMapper.listAll();
    }
}
