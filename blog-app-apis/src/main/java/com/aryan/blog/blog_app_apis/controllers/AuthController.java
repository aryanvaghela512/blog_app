package com.aryan.blog.blog_app_apis.controllers;

import com.aryan.blog.blog_app_apis.exeptions.InvalidCredentialsException;
import com.aryan.blog.blog_app_apis.payloads.JwtAuthRequest;
import com.aryan.blog.blog_app_apis.payloads.JwtAuthResponse;
import com.aryan.blog.blog_app_apis.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/v1/auth/")
public class AuthController {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
    ) throws Exception {
        authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtHelper.generateToken(userDetails);

        JwtAuthResponse response = JwtAuthResponse.builder().token(token).build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private void authenticate(String userName, String password) throws Exception {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            System.out.println("Invalid Details");
            throw new InvalidCredentialsException("Invalid Username or Password");
        }
    }
}
