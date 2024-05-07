package com.example.managersystem.config;

import com.example.managersystem.filter.RoleAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author conglingyan
 * @Date 2024/5/6 16:42
 * @Version 1.0
 * @Description 描述：拦截器配置类
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RoleAccessInterceptor())
                .addPathPatterns("/**");
    }
}

