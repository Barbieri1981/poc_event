package com.poc.security.service;

import com.poc.security.constants.Constants;
import com.poc.security.entity.Rol;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthoritiesFactory {

    public Set<SimpleGrantedAuthority> createAuthorities(final List<Rol> roles) {
        final Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(rol -> authorities.add(new SimpleGrantedAuthority(Constants.ROLE + rol.getDescription())));
        return authorities;
    }
}

