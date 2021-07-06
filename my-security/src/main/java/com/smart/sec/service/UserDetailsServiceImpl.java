package com.smart.sec.service;

import com.smart.sec.entity.User;
import com.smart.sec.util.MyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-14 16:40
 **/
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.selectByUsername(s);
        log.info(user.toString());
        return new MyUserDetails(user);
    }
}
