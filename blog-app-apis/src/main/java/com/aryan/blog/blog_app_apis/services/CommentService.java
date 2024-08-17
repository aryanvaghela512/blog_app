package com.aryan.blog.blog_app_apis.services;

import com.aryan.blog.blog_app_apis.payloads.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, Integer postId);
    void deleteComment(Integer commentId);
}
