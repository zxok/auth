package com.smart.sec;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-14 15:05
 **/
@SpringBootApplication
@MapperScan("com.smart.sec.mapper")
public class MySecApp {
    public static void main(String[] args) {
        SpringApplication.run(MySecApp.class,args);
    }
}
