package com.aryan.blog.blog_app_apis.repositories;

import com.aryan.blog.blog_app_apis.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositories extends JpaRepository<Category,Integer> {

}
