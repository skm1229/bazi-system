package com.bazi.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Jackson配置类
 * 解决前端JavaScript数字精度问题，将Long类型序列化为字符串
 *
 * @author skm1229
 * @version 1.0.0
 */
@Configuration
public class JacksonConfig {

    /**
     * 配置Jackson ObjectMapper
     * 将Long类型和long类型序列化为字符串，避免JavaScript精度丢失
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        
        // 创建自定义模块
        SimpleModule module = new SimpleModule();
        
        // 将Long类型序列化为字符串
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);
        
        // 注册模块
        objectMapper.registerModule(module);
        
        return objectMapper;
    }
}
