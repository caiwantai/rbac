package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.common.PageResult;
import cn.wolfcode.rbac.common.QueryObject;
import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.mapper.DepartmentMapper;
import cn.wolfcode.rbac.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public  PageResult<List<Department>> selectByCondition(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Department> departments = departmentMapper.listDepartment();
        PageInfo<Department> pageInfo = new PageInfo<>(departments);
        PageResult<List<Department>> pageResult = new PageResult<>();
        pageResult.setData(departments);
        pageResult.setCurrentPage(pageInfo.getPageNum());
        pageResult.setTotalPage(pageInfo.getPages());
        return pageResult ;
    }

    @Override
    public Department selectById(Long id) {
        return departmentMapper.selectById(id);
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }
    @Transactional
    @Override
    public int insert(Department record) {
        return departmentMapper.insert(record);
    }
    @Transactional
    @Override
    public int updateByPrimaryKey(Department record) {
        return departmentMapper.updateByPrimaryKey(record);
    }
}
