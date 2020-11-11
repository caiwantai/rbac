package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.annotation.PermissionAllowed;
import cn.wolfcode.rbac.common.EmployeeQueryObject;
import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PermissionAllowed("employee:list")
    @RequestMapping("/list")
    public String list(Map<String,Object> model, @ModelAttribute("qo") EmployeeQueryObject qo) {
        Map<String, Object> map = employeeService.listAllCondition(qo);
        model.putAll(map);
        return "employee/list";
    }
    @PermissionAllowed("employee:input")
    @RequestMapping("/input")
    public String input(@RequestParam(value = "id", required = false) Long id, Map<String,Object> model) {
        model.putAll(employeeService.listDepartmentAndRole());
        // add
        if (Objects.isNull(id)) {
            return "employee/input";
        }
        // save  程序不够健壮 要对空值判断
        Employee employee = employeeService.selectById(id);

        model.put("employee", employee);
        return "employee/input";
    }

    @PermissionAllowed("employee:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate( Employee employee,String roleIds) {
        // save
        if (Objects.isNull(employee.getId())) {
            employeeService.insert(employee, roleIds);
        } else {
            // update
            employeeService.updateByPrimaryKey(employee,roleIds);
        }
        return "redirect:/employee/list";
    }

    @PermissionAllowed("employee:delete")
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        employeeService.deleteByPrimaryKey(id);
        return "redirect:/employee/list";
    }
}
