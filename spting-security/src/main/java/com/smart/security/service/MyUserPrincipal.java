package com.smart.security.service;

import com.smart.security.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-12 14:57
 **/
public class MyUserPrincipal implements UserDetails {
    private Admin admin;

    public MyUserPrincipal(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return admin.getIsAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return admin.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return admin.getIsCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return admin.getIsEnabled();
    }
}


