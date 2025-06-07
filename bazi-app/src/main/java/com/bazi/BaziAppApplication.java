package com.bazi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 八字排盘管理系统应用启动类
 *
 * @author skm1229
 * @version 1.0.0
 */
@SpringBootApplication
@MapperScan("com.bazi.mapper")
public class BaziAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaziAppApplication.class, args);
        System.out.println("八字排盘管理系统启动成功！");
        System.out.println("Swagger文档地址: http://localhost:9999/swagger-ui.html");
    }
}
