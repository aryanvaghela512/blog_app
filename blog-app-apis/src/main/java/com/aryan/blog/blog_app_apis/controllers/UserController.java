package com.aryan.blog.blog_app_apis.controllers;

import com.aryan.blog.blog_app_apis.payloads.UserDTO;
import com.aryan.blog.blog_app_apis.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    //Post Create user
    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO cretaedUser = this.userServices.createUser(userDTO);
        return new ResponseEntity<>(cretaedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userID}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable int userID) {
        UserDTO updatedUser = this.userServices.updateUser(userDTO, userID);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable int userID) {
        userServices.deleteUser(userID);
        return new ResponseEntity<>("Delete User Sucessfully", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return new ResponseEntity<>(userServices.getAllUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getSingleUser(@PathVariable int userID) {
        return new ResponseEntity<>(userServices.getUserById(userID), HttpStatus.FOUND);
    }

}
