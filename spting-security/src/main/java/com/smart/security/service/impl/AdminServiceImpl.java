package com.smart.security.service.impl;

import com.smart.security.entity.Admin;
import com.smart.security.mapper.AdminMapper;
import com.smart.security.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-14 13:51
 **/
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    AdminMapper adminMapper;

    @Override
    public Admin selectAdminById(int id) {
        Admin admin = adminMapper.selectById(id);
        log.info(admin.toString());
        return admin;
    }
}
