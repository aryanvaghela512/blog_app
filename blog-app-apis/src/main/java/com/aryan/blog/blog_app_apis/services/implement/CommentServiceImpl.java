package com.aryan.blog.blog_app_apis.services.implement;

import com.aryan.blog.blog_app_apis.entity.Comment;
import com.aryan.blog.blog_app_apis.entity.Post;
import com.aryan.blog.blog_app_apis.exeptions.ResourceNotFoundException;
import com.aryan.blog.blog_app_apis.payloads.CommentDTO;
import com.aryan.blog.blog_app_apis.repositories.CommentRepo;
import com.aryan.blog.blog_app_apis.repositories.PostRepo;
import com.aryan.blog.blog_app_apis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Id",postId));
        Comment comment=modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        return modelMapper.map(commentRepo.save(comment), CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","Id",commentId));
        commentRepo.delete(comment);
    }
}
