package com.smart.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.auth.entity.Member;
import com.smart.auth.mapper.MemberMapper;
import com.smart.auth.service.RegisterService;
import com.smart.auth.shiro.ShiroUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-11 19:52
 **/
@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    MemberMapper memberMapper;

    @Override
    public int add(Member member) {
        int insert = 0;
        Member memberIs = memberMapper.selectOne(new QueryWrapper<Member>().lambda().eq(Member::getUsername, member.getUsername()));
        if (memberIs == null) {
            //对密码进行加密
            member.setPassword(ShiroUtils.encrypt(member.getPassword()));
            insert = memberMapper.insert(member);
        }
        return insert;
    }
}
