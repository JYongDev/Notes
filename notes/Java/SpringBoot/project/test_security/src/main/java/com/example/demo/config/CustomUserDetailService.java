package com.example.demo.config;

import com.example.demo.bean.Role;
import com.example.demo.bean.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        User user = userService.selectByName(s);

        if (user == null) {
            return null;
        }

        List<Role> roles = roleService.listByUserId(user.getId());

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));

        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authorities);
    }

}
