package com.lucky.config;

import com.lucky.utils.JwtLoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private JwtLoginInterceptor jwtLoginInterceptor;  // 注入 Spring 管理的 Bean
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtLoginInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns(
                        "/user/login"
                );
    }
}
