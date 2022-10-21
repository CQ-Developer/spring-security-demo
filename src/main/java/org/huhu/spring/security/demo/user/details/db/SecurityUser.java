package org.huhu.spring.security.demo.user.details.db;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 通过适配器模式,
 * 将数据库表映射到SpringSecurity的 {@link UserDetails} 接口.
 * 并不推荐将数据库表的映射类和 {@link UserDetails} 融合为一个类
 */
public class SecurityUser implements UserDetails {

    private final UserTable user;

    public SecurityUser(UserTable user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(user::getAuthority);
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
