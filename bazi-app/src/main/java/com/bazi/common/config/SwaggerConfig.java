package com.bazi.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置类
 * 
 * @author skm1229
 * @version 1.0.0
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("八字排盘管理系统 API")
                        .description("提供八字、奇门遁甲、紫微斗数、六爻、梅花易数等排盘功能的REST API接口")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("skm1229")
                                .email("skm1229@example.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
