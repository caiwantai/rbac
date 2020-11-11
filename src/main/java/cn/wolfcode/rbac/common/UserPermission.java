package cn.wolfcode.rbac.common;

import cn.wolfcode.rbac.domain.Permission;
import lombok.Data;

import java.util.List;

@Data
public class UserPermission {
    private Long id;
    private String name;
    private Boolean admin;
    private List<Permission> permissions;
}
