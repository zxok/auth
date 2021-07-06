package com.smart.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-12 10:57
 **/
@SpringBootApplication
@MapperScan("com.smart.security.mapper")
public class SecurityApp {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApp.class,args);
    }
}
