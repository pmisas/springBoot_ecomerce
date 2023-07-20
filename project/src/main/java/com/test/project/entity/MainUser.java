package com.test.project.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MainUser {
    private String name;
    private String email;
    private String password;
    private String address;
    private Collection<? extends GrantedAuthority> authorities;

    public MainUser(String name, String email, String password, String address, List<Item> items, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.authorities = authorities;
    }

    public static MainUser build(User user) {
        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                        .getRolName().name())).collect(Collectors.toList());
        return new MainUser(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getAddress(),
                user.getItems(),
                authorities);
    }

    //@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //@Override
    public String getPassword() {
        return password;
    }

    //@Override
    public String getUsername() {
        return email;
    }

    //@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //@Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //@Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //@Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
