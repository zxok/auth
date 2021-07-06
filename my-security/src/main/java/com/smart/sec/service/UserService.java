package com.smart.sec.service;

import com.smart.sec.entity.User;

/**
 * @author Hzx
 */
public interface UserService {

    /**
     * 用户名
     * @param username
     * @return
     */
    User selectByUsername(String username);
}
