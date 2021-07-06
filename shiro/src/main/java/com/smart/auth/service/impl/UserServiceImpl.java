package com.smart.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.auth.dto.MemberDto;
import com.smart.auth.entity.Member;
import com.smart.auth.mapper.MemberMapper;
import com.smart.auth.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    MemberMapper memberMapper;

    @Override
    public MemberDto findByUsername(String username) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Member.COL_USERNAME, username);
        Member member = memberMapper.selectOne(queryWrapper);
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member,memberDto);
        return memberDto;
    }
}
