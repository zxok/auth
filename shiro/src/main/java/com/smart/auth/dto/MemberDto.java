package com.smart.auth.dto;

import com.smart.auth.entity.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 通过用户Id所有的权限
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MemberDto extends Member implements Serializable {
    private Integer id;
    private List<RoleDto> roles;
}
