package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DepartmentMapper {
    List<Department> listDepartment();

    Department selectById( @Param("id") Long id);

    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(Department record);

    int updateByPrimaryKey(Department record);
}
