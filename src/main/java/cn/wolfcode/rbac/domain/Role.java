package cn.wolfcode.rbac.domain;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private Long id;
    private String name;
    private String sn;
    List<Permission> permissions;
}
