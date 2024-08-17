package com.aryan.blog.blog_app_apis.services.implement;

import com.aryan.blog.blog_app_apis.entity.Category;
import com.aryan.blog.blog_app_apis.exeptions.ResourceNotFoundException;
import com.aryan.blog.blog_app_apis.payloads.CategoryDTO;
import com.aryan.blog.blog_app_apis.repositories.CategoryRepositories;
import com.aryan.blog.blog_app_apis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepositories categoryRepositories;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category map = modelMapper.map(categoryDTO, Category.class);
        Category save = categoryRepositories.save(map);
        return modelMapper.map(save,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category category = categoryRepositories.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category saved = categoryRepositories.save(category);
        return modelMapper.map(saved,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepositories.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepositories.delete(category);

    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        Category category = categoryRepositories.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<CategoryDTO> list = new ArrayList<>();
        for (Category cat : categoryRepositories.findAll()){
            list.add(modelMapper.map(cat,CategoryDTO.class));
        }
        return list;
    }
}
