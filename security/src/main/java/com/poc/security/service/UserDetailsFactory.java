package com.poc.security.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserDetailsFactory {

    public UserDetails createUserDetails(String username, String password, Set<SimpleGrantedAuthority> authorities) {
        return new User(username, password, authorities);
    }
}
