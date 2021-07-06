package com.smart.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Hzx
 */
@SpringBootApplication
@MapperScan("com.smart.auth.mapper")
public class ShiroApp {
    public static void main(String[] args) {
        SpringApplication.run(ShiroApp.class, args);
    }

}
