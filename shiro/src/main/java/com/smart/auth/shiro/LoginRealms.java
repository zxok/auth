package com.smart.auth.shiro;

import com.smart.auth.dto.MemberDto;
import com.smart.auth.dto.RoleDto;
import com.smart.auth.mapper.MemberRoleMapper;
import com.smart.auth.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Hzx
 */
public class LoginRealms extends AuthorizingRealm {
    /**
     * 认证
     * @param authenticationToken 登录创建的UsernamePasswordToken
     * @return
     * @throws AuthenticationException
     */
    @Resource
    private UserService userService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 第一步 从token中取出用户名，这个用户名是用户在页面输入的信息，传递给token
        String username = (String) authenticationToken.getPrincipal();
        // 第二步 通过获取到的用户从数据库中查询用户信息
        MemberDto memberDto = userService.findByUsername(username);
        // 第三步 业务逻辑判断
        if (memberDto == null) {
            throw new UnknownAccountException("账号步存在");
        }
        if (memberDto.getLocked()) {
            throw new LockedAccountException("账号被锁定,请于管理员联系");
        }
        /**
         * 第四步 创建AuthenticationInfo对象
         * Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName
         *
         * Object principal -- 从数据库中查询的user对象; 也可以是用户名(不推荐)
         * Object hashedCredentials -- 从数据库中获取的用户的密码
         * ByteSource credentialsSalt -- 加密盐–用于加密密码对比,为了防止两用户的初始密码是一样的
         * realmName -- 从token中获取的用户名
         */
        return new SimpleAuthenticationInfo(memberDto, memberDto.getPassword(), null, username);
    }

    /**
     * 授权
     * 获取权限
     *
     * @param principalCollection
     * @return
     */
    @Resource
    MemberRoleMapper memberRoleMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前登录的用户信息
        MemberDto memberDto = (MemberDto) principalCollection.getPrimaryPrincipal();
        // 通过会员id，从数据库中查询权限角色
        List<RoleDto> roles = memberRoleMapper.selectRolesById(memberDto.getId());
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        roles.forEach(role -> {
            roleSet.add(role.getRoleName());
            role.getPermissions().forEach((permission -> {
                String perName = permission.getPerName();
                permissionSet.add(perName);
            }));
        });

        //  SimpleAuthorizationInfo 设置权限集合 跟角色集合
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}
