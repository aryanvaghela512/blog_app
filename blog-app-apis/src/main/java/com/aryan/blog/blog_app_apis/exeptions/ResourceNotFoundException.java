package com.aryan.blog.blog_app_apis.exeptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    String resourceName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue) {
        super(String.format("%s not with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldValue=fieldValue;
        this.fieldName=fieldName;
    }
}
