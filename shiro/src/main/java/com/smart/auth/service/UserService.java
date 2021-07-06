package com.smart.auth.service;

import com.smart.auth.dto.MemberDto;

public interface UserService {

    MemberDto findByUsername(String username);

}
