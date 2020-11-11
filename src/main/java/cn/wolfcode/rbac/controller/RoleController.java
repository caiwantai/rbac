package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.common.PageResult;
import cn.wolfcode.rbac.common.QueryObject;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.service.PermissionService;
import cn.wolfcode.rbac.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Role> roles = roleService.listAll();
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        PageResult<List<Role>> pageResult = new PageResult<>();
        pageResult.setData(roles);
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setCurrentPage(pageInfo.getPageNum());
        model.addAttribute("pageResult", pageResult);
        return "role/list";
    }


    @RequestMapping("/input")
    public String input(@RequestParam(value = "id", required = false) Long id, Model model) {
        List<Permission> permissions = permissionService.listNoPage();
        model.addAttribute("permissions", permissions);
        // add
        if (Objects.isNull(id)) {
            return "role/input";
        }
        // save  程序不够健壮 要对空值判断
        Role role = roleService.selectById(id);
        model.addAttribute("role", role);
        return "role/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role, String permissionIds) {
        // save
        if (Objects.isNull(role.getId())) {
           roleService.insert(role,permissionIds);
        } else {
            // update
            roleService.updateByPrimaryKey(role,permissionIds);
        }
        return "redirect:/role/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        roleService.deleteByPrimaryKey(id);
        return "redirect:/role/list";
    }

}
