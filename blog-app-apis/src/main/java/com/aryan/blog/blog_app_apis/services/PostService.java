package com.aryan.blog.blog_app_apis.services;

import com.aryan.blog.blog_app_apis.entity.Post;
import com.aryan.blog.blog_app_apis.payloads.PostDTO;
import com.aryan.blog.blog_app_apis.payloads.PostResponse;

import java.util.List;

public interface PostService {
    //create
    PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId );

    //update
    PostDTO updatePost(PostDTO postDTO,Integer postId);

    //delete
    void deletePost(Integer postId);

    //getAll
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get
    PostDTO getPostById(Integer postId);

    //getAllPostBycategory
    PostResponse getPostBycategory(Integer categoryId,Integer pageNumber,Integer pageSize);


    //get all post by user
    PostResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize);

    //searchPost
    List<PostDTO> searchPost(String keyword);
}
