package com.aryan.blog.blog_app_apis.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostDTO> content;
    private int pageNumber;
    private int pageSize;
    private Long totalElement;
    private int totalPages;
    private boolean lastPage;
}
