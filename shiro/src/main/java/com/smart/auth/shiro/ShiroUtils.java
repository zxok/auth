package com.smart.auth.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

/**
 * @author Hzx
 */
public class ShiroUtils {
    public static final int DEFAULT_ITERATIONS = 100;

    /**
     *  注册的时候对密码进行加密
     */
    public static String encrypt(String password) {
        return new SimpleHash(Sha256Hash.ALGORITHM_NAME, password, null, DEFAULT_ITERATIONS).toHex();
    }

    /**
     *  登出
     */
    public static void logout() {
        getSubject().logout();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     *  加密测试
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(encrypt("123456"));
    }

}
