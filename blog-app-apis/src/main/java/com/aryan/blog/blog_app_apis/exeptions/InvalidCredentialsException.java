package com.aryan.blog.blog_app_apis.exeptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){
        super();
    }
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
