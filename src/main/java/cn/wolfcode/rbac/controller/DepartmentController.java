package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.annotation.PermissionAllowed;
import cn.wolfcode.rbac.common.PageResult;
import cn.wolfcode.rbac.common.QueryObject;
import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PermissionAllowed("department:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        PageResult<List<Department>> pageResult = departmentService.selectByCondition(qo);
        model.addAttribute("pageResult", pageResult);
        return "department/list";
    }

    @PermissionAllowed("department:input")
    @RequestMapping("/input")
    public String input(@RequestParam(value = "id", required = false) Long id, Model model) {
        // add
        if (Objects.isNull(id)) {
            return "department/input";
        }
        // save  程序不够健壮 要对空值判断
        Department department = departmentService.selectById(id);

        model.addAttribute("d", department);
        return "department/input";
    }

    @PermissionAllowed("department:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Department department) {
        // save
        if (Objects.isNull(department.getId())) {
            departmentService.insert(department);
        } else {
            // update
            departmentService.updateByPrimaryKey(department);
        }
        return "redirect:/department/list";
    }

    @PermissionAllowed("department:delete")
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        departmentService.deleteByPrimaryKey(id);
        return "redirect:/department/list";
    }

}
