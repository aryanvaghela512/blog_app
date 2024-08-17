package com.aryan.blog.blog_app_apis.security;

import com.aryan.blog.blog_app_apis.entity.User;
import com.aryan.blog.blog_app_apis.exeptions.ResourceNotFoundException;
import com.aryan.blog.blog_app_apis.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepositories userRepositories;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        User user = userRepositories.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("user", "email" + username, 0));
        return user;
    }
}
