package com.qy.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @version v1.0
 * @author: qy
 * @description:
 * @date: 2022/1/18
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class JobApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }

}
