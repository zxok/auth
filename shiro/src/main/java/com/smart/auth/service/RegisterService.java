package com.smart.auth.service;

import com.smart.auth.entity.Member;

/**
 *  注册
 * @author Hzx
 */
public interface RegisterService {

    /**
     *  用户注册
     * @param member
     * @return
     */
    int add(Member member);
}
