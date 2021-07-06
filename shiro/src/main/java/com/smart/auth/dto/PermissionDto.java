package com.smart.auth.dto;

import com.smart.auth.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionDto extends Permission {
    private List<RoleDto> roles;
}
