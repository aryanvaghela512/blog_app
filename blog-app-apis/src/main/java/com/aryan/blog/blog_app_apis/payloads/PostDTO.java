package com.aryan.blog.blog_app_apis.payloads;

import com.aryan.blog.blog_app_apis.entity.Category;
import com.aryan.blog.blog_app_apis.entity.Comment;
import com.aryan.blog.blog_app_apis.entity.User;
import lombok.*;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostDTO {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addDate;
    private CategoryDTO category;
    private List<CommentDTO> comments;


}
