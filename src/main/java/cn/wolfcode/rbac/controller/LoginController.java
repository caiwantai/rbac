package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.common.utils.SessionUtil;
import cn.wolfcode.rbac.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        boolean b = employeeService.checkNameAndPassword(username, password);
        if (b){
            return "redirect:/common/index";
        }
        model.addAttribute("errorMessage","账号或者密码错误");
        return "common/login";
    }
    @RequestMapping("/common/index")
    public String index(){
        return "common/index";
    }

    @RequestMapping("/logout")
    public String login(){
        SessionUtil.removeSession();
        return "redirect:/";
    }
}
