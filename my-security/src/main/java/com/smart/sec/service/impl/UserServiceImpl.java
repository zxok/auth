package com.smart.sec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.sec.entity.User;
import com.smart.sec.mapper.UserMapper;
import com.smart.sec.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-14 16:54
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User selectByUsername(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        return user;
    }
}
