package cn.wolfcode.rbac.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeQueryObject extends QueryObject  {
    private String keyword;
    private Long deptId = -1L;
}
