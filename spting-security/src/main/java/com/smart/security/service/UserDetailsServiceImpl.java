package com.smart.security.service;

import com.smart.security.entity.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-12 14:43
 **/
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin = adminService.selectAdminById(1);
        return new MyUserPrincipal(admin);
    }
}
