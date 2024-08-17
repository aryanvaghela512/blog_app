package com.aryan.blog.blog_app_apis.controllers;

import com.aryan.blog.blog_app_apis.payloads.CategoryDTO;
import com.aryan.blog.blog_app_apis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    //create
    @PostMapping()
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO category = this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<CategoryDTO>(category, HttpStatus.CREATED);
    }


    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId){
        CategoryDTO categoryDTO1 = this.categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(categoryDTO1,HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Delete Category Succesfully",HttpStatus.OK);
    }


    //get
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getGategoryById(@PathVariable Integer categoryId){
        CategoryDTO category = categoryService.getCategory(categoryId);
        return new ResponseEntity<CategoryDTO>(category,HttpStatus.FOUND);
    }
    //getALl
    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        List<CategoryDTO> all = categoryService.getAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
}

