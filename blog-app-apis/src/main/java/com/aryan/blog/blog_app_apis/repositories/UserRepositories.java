package com.aryan.blog.blog_app_apis.repositories;

import com.aryan.blog.blog_app_apis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User,Integer>{
    Optional<User> findByEmail(String email);

}
