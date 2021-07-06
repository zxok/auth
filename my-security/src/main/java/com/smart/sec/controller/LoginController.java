package com.smart.sec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-14 17:34
 **/

@RestController
public class LoginController {

    @GetMapping("/login")
    public String login(String username, String password) {
        return "login";
    }
}
