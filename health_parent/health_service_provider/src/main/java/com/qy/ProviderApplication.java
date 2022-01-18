package com.qy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @version v1.0
 * @author: qy
 * @description:
 * @date: 2022/1/13
 */
@SpringBootApplication
@MapperScan(basePackages = "com.qy.mapper")
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
