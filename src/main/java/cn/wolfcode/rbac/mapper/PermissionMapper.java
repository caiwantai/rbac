package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {

    List<Permission> listAll();

    int deleteById(Long id);

    List<Permission> getPermissionByEmId(@Param("emId") Long emId);

    int insertByRoleId(@Param("roleId") Long roleId,@Param("perId") Long perId);

    void deleteByRoleId(@Param("roleId") Long roleId);
}
