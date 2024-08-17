package com.aryan.blog.blog_app_apis.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class globalExeptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExeptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((err)->{
            String fieldName = ((FieldError) err).getField();
            String defaultMessage = err.getDefaultMessage();
            errors.put(fieldName,defaultMessage);
        });
        return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
    }
}
