package com.smart.auth.mapper;

import com.smart.auth.dto.PermissionDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {
    List<PermissionDto> selectByRoleId(@Param("roleId") int roleId);
}
