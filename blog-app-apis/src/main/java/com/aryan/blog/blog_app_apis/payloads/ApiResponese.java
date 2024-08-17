package com.aryan.blog.blog_app_apis.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponese {
    private String message;
    private boolean isSuccess;
}
