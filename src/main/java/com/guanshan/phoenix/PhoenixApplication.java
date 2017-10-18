package com.guanshan.phoenix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.guanshan.phoenix.dao.mapper")
@EnableTransactionManagement
public class PhoenixApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PhoenixApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PhoenixApplication.class);
	}
}
