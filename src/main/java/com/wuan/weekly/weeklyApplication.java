package com.wuan.weekly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.wuan.weekly.mapper","com.wuan.weekly.dao"})
public class weeklyApplication {

	public static void main(String[] args) {
		SpringApplication.run(weeklyApplication.class, args);
	}
}
