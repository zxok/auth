package com.smart.auth.controller;

import com.smart.auth.entity.Member;
import com.smart.auth.service.RegisterService;
import com.smart.auth.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Hzx
 */
@RestController
@Slf4j
public class LoginController {

    @Resource
    RegisterService registerService;

    /**
     *  登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(String username, String password) {
        //登录 创建UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 认证
        try {
            // 获取Subject
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            log.info("登录成功");
        } catch (AccountException e) {
            log.info("账号异常");
            return "账号异常";
        } catch (IncorrectCredentialsException exception) {
            log.info("账号或密码错误!");
            return "账号或密码错误";
        }
        //获取session
        String jSessionId = SecurityUtils.getSubject().getSession().getId().toString();
        return "登录成功-JSessionId:"+jSessionId;
    }

    /**
     *  注册
     * @param member
     * @return
     */
    @PostMapping("/register")
    public String register(@RequestBody Member member) {
        try {
            if (registerService.add(member) != 1) {
                return "注册失败，该用户已存在！";
            }
        } catch (Exception exception) {
            return "网络异常！";
        }
        return "注册成功";
    }

    /**
     *  登出
     * @return
     */
    @GetMapping("/logout")
    @RequiresAuthentication
    public String logout() {
        return "登出";
    }

    @GetMapping("/test")
    @RequiresAuthentication
    public String test() {
        ShiroUtils.logout();
        return "test";
    }
}
