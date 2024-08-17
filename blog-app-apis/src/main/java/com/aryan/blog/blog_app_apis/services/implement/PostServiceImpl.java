package com.aryan.blog.blog_app_apis.services.implement;

import com.aryan.blog.blog_app_apis.entity.Category;
import com.aryan.blog.blog_app_apis.entity.Post;
import com.aryan.blog.blog_app_apis.entity.User;
import com.aryan.blog.blog_app_apis.exeptions.ResourceNotFoundException;
import com.aryan.blog.blog_app_apis.payloads.PostDTO;
import com.aryan.blog.blog_app_apis.payloads.PostResponse;
import com.aryan.blog.blog_app_apis.repositories.CategoryRepositories;
import com.aryan.blog.blog_app_apis.repositories.PostRepo;
import com.aryan.blog.blog_app_apis.repositories.UserRepositories;
import com.aryan.blog.blog_app_apis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
 public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private CategoryRepositories categoryRepositories;

    @Override
    public PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId ){
        User user = userRepositories.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserID",userId));
        Category category = categoryRepositories.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        Post post = modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post saved = postRepo.save(post);
        return modelMapper.map(saved,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());
        Post saved = postRepo.save(post);
        return modelMapper.map(saved,PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort = Sort.by(sortBy);
        if (sortDir.equalsIgnoreCase("desc")){
            sort=sort.descending();
        }
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePost = postRepo.findAll(pageable);
        List<Post> content = pagePost.getContent();
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post:content){
            postDTOS.add(modelMapper.map(post,PostDTO.class));
        }
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOS);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        return modelMapper.map(post,PostDTO.class);
    }

    @Override
    public PostResponse getPostBycategory(Integer categoryId,Integer pageNumber,Integer pageSize) {
        Category category = categoryRepositories.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost = postRepo.findByCategory(category, pageable);
        List<Post> content = pagePost.getContent();
        List<PostDTO> dtos = new ArrayList<>();
        for (Post post:content){
            dtos.add(modelMapper.map(post,PostDTO.class));
        }
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(dtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize) {
        User user=userRepositories.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost = postRepo.findByUser(user, pageable);
        List<Post> content = pagePost.getContent();
        List<PostDTO> dtos = new ArrayList<>();
        for (Post post:content){
            dtos.add(modelMapper.map(post,PostDTO.class));
        }
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(dtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;

    }

    @Override
    public List<PostDTO> searchPost(String keyword) {
        List<Post> byTitleContaining = postRepo.findByTitleContaining(keyword);
        List<PostDTO> list = new ArrayList<>();
        for (Post post:byTitleContaining){
            list.add(modelMapper.map(post,PostDTO.class));
        }
        return list;
    }
}
