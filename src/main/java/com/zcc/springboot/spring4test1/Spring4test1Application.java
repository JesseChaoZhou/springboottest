package com.zcc.springboot.spring4test1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.zcc.springboot.spring4test1.mapper")
@EnableCaching
public class Spring4test1Application {
	public static void main(String[] args) {

		SpringApplication.run(Spring4test1Application.class, args);
		//my name is zcc .
	}
}
