package com.aryan.blog.blog_app_apis.payloads;

import lombok.Builder;
import lombok.Data;
import org.modelmapper.spi.Tokens;

@Data
@Builder
public class JwtAuthResponse {
    private String token;


}
