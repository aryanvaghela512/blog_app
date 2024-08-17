package com.aryan.blog.blog_app_apis.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private int Id;

    @NotEmpty
    @Size(min = 4,message = "Min size of category title is 4")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 10,message = "Min size of category description is 10")
    private String categoryDescription;
}
