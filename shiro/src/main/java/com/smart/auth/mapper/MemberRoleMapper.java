package com.smart.auth.mapper;

import com.smart.auth.dto.RoleDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberRoleMapper {
    List<RoleDto> selectRolesById(@Param("memeberId") int memeberId);
}
