package com.aryan.blog.blog_app_apis.services;

import com.aryan.blog.blog_app_apis.payloads.UserDTO;

import java.util.List;

public interface UserServices{

    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user,Integer userID);
    UserDTO getUserById(Integer userId);
    List<UserDTO> getAllUsers();
    void deleteUser(Integer userId);
}
