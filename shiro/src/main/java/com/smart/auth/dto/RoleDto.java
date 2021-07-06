package com.smart.auth.dto;

import com.smart.auth.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDto  extends Role {
    private List<PermissionDto> permissions;
}
