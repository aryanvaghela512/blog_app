package com.aryan.blog.blog_app_apis.repositories;

import com.aryan.blog.blog_app_apis.entity.Category;
import com.aryan.blog.blog_app_apis.entity.Post;
import com.aryan.blog.blog_app_apis.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
    Page<Post> findByUser(User user, Pageable pageable);
    Page<Post> findByCategory(Category category, Pageable pageable);
    List<Post> findByTitleContaining(String title);
}
