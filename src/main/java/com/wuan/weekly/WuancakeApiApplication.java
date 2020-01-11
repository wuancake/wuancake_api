package com.wuan.weekly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@PropertySource("file:/etc/application.properties")
@SpringBootApplication
@MapperScan("com.wuan.weekly.mapper")
public class WuancakeApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WuancakeApiApplication.class, args);
    }
}
