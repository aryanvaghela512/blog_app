package com.aryan.blog.blog_app_apis.controllers;

import com.aryan.blog.blog_app_apis.payloads.ApiResponese;
import com.aryan.blog.blog_app_apis.payloads.CommentDTO;
import com.aryan.blog.blog_app_apis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,
                                                    @PathVariable Integer postId) {
        return new ResponseEntity<>(commentService.createComment(commentDTO, postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponese> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponese("Comment Deleted Successfully", true), HttpStatus.OK);
    }
}
