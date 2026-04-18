package com.tasktimetracker.entity;

import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * фиктивыне данные для jwt
 */
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private String username;

    public UserDetailsImpl (String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("MANAGER"));
    }

    @Override
    public @Nullable String getPassword() {
        return null;
    }

}