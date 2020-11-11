package cn.wolfcode.rbac.domain;

import lombok.Data;

import java.util.List;

@Data
public class Employee {
    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer age;
    private Boolean admin = false;
    private Department dept;
    private List<Role> roles;
}
