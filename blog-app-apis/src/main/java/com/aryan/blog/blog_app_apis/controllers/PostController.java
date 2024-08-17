package com.aryan.blog.blog_app_apis.controllers;

import com.aryan.blog.blog_app_apis.config.AppConstants;
import com.aryan.blog.blog_app_apis.payloads.PostDTO;
import com.aryan.blog.blog_app_apis.payloads.PostResponse;
import com.aryan.blog.blog_app_apis.services.FileService;
import com.aryan.blog.blog_app_apis.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId) {

        PostDTO post = postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<PostDTO>(post, HttpStatus.CREATED);
    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<?> getPostsByUser(@PathVariable Integer userId, @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber, @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize) {
        PostResponse postByUser = postService.getPostByUser(userId, pageNumber, pageSize);
        return new ResponseEntity<>(postByUser, HttpStatus.FOUND);
    }

    //get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<?> getPostsByCategory(@PathVariable Integer categoryId, @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber, @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize) {
        PostResponse postBycategory = postService.getPostBycategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<>(postBycategory, HttpStatus.FOUND);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPost(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize, @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        PostResponse postResponse = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.FOUND);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Integer postId) {
        PostDTO postById = postService.getPostById(postId);
        return new ResponseEntity<>(postById, HttpStatus.FOUND);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>("Post Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/st/{postId}")
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId) {
        PostDTO postDTO1 = postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(postDTO1, HttpStatus.OK);
    }

    //search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<?> searchPostByTitle(@PathVariable("keywords") String keywords) {
        List<PostDTO> postDTOS = postService.searchPost(keywords);
        return new ResponseEntity<>(postDTOS, HttpStatus.FOUND);

    }

    //post image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<?> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException {
        PostDTO postById = postService.getPostById(postId);
        String fileName = fileService.upLoadImage(path, image);
        postById.setImageName(fileName);
        PostDTO postDTO = postService.updatePost(postById, postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);

    }

    //method to serve file
    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }


}

