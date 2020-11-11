package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.annotation.PermissionAllowed;
import cn.wolfcode.rbac.common.PageResult;
import cn.wolfcode.rbac.common.QueryObject;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PermissionAllowed("permission:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        PageResult<List<Permission>> pageResult = permissionService.listAll(qo);
        model.addAttribute("pageResult", pageResult);
        return "permission/list";
    }

    @PermissionAllowed("permission:delete")
    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        permissionService.deleteById(id);
        return "redirect:/permission/list";
    }
}
