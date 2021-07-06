package com.smart.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-15 17:40
 **/
@SpringBootTest
public class BCryptPasswordEncoderTest {
    @Resource
    BCryptPasswordEncoder encoder;

    @Test
    void test(){
        System.out.println(encoder.encode("123"));
    }
}
