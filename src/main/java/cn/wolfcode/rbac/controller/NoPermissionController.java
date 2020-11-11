package cn.wolfcode.rbac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class NoPermissionController {
    @RequestMapping("/nopermission")
    public String noPermission(){
        return "common/nopermission";
    }

    @RequestMapping("/error")
    public String error(){
        return "common/error";
    }
}
