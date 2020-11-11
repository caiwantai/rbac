package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Role> listAll();

    Role selectById( @Param("id") Long id);

    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(Role record);

    int updateByPrimaryKey(Role record);

}
