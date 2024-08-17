package com.aryan.blog.blog_app_apis.services.implement;

import com.aryan.blog.blog_app_apis.entity.User;
import com.aryan.blog.blog_app_apis.exeptions.ResourceNotFoundException;
import com.aryan.blog.blog_app_apis.payloads.UserDTO;
import com.aryan.blog.blog_app_apis.repositories.UserRepositories;
import com.aryan.blog.blog_app_apis.services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.DtoToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepositories.save(user);
        return this.UserToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userID) {
        User user = this.userRepositories.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "id", userID));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        User updatedUser = this.userRepositories.save(user);
        UserDTO userDTO1 = this.UserToDto(updatedUser);
        return userDTO1;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = userRepositories.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.UserToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepositories.findAll();
        List<UserDTO> UserDTOS = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
        return UserDTOS;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepositories.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepositories.delete(user);
    }

    private User DtoToUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
//        user.setId(userDTO.getId());
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setAbout(userDTO.getAbout());
        return user;
    }

    public UserDTO UserToDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName())
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setAbout(user.getAbout());
        return userDTO;
    }
}
