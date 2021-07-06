package com.smart.auth.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限控制
 * 权限注解
 * 基于角色的注解
 * 基于权限的注解
 * @author Hzx
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/roles")
    @RequiresRoles({"root"})
    public String roles() {
        return "访问成功，角色：root";
    }

    @GetMapping("/per")
    @RequiresPermissions ({"/admin"})
    public String per() {
        return "访问成功，需要admin权限才能访问";
    }
}
