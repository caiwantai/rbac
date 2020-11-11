package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.common.QueryObject;
import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
   List<Employee> listAllCondition(QueryObject qo);

   Employee selectById(@Param("id")Long id);

   int  deleteByPrimaryKey(@Param("id") Long id);

   void updateByPrimaryKey(Employee employee);

   int insert(Employee employee);

   List<Role> selectRoleByEmId(@Param("emId")  Long emId);

   int insertEmployeeAndRoles(@Param("emId") Long emId, @Param("roleId")Long roleId);

   int deleteRolesByEmId(@Param("emId")  Long emId);

   Employee selectByName(@Param("name") String name);
}
