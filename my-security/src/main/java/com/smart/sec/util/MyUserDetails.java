package com.smart.sec.util;

import com.smart.sec.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-14 16:43
 **/
@Data
public class MyUserDetails implements UserDetails {

    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getIsAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsEnabled();
    }
}
