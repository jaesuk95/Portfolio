package com.portfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@Profile({"cross"})
public class CrossOriginConfigure implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // https://chowdera.com/2022/03/202203082045152102.html
        registry
                .addMapping("/**")
                .allowedOrigins("*")
//                .allowedOriginPatterns("*")
//                .allowedOrigins("http://localhost:8080", "http://localhost:4000",
//                        "http://localhost:3000",
//                        "http://192.168.1.152:3000",
//                        "http://192.168.1.152:4000")
//                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "OPTIONS", "FETCH", "PUT", "DELETE", "PATCH");
//                .allowCredentials(true);
    }
}

