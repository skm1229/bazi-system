package com.bazi.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类：静态资源
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 跨域处理
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 添加所有映射
                .allowCredentials(true) // 是否发送Cookie
                .allowedOriginPatterns("*") // 放行所有原始域
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 放行指定类型请求
                .allowedHeaders("*")
                .exposedHeaders("*");
    }


}