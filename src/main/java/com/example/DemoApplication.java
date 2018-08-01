package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @SpringBootApplication 标注一个主程序类，说明这是个SpringBoot应用主配置类，SpringBoot就应该用这个类的main方法启动,
 * 		里面的自动配置注解会扫描这个类及这个类所在包及其所有子包，
 * 		同时会自动导入需要的自动配置类，免去手动配置和导入功能组件，自动配置jar包为：spring-boot-autoconfigure.jar其配置值其实都在类的META-INF/spring.factories中
 */
@SpringBootApplication
//		(scanBasePackages = "com.example.controller")
//@ComponentScan(basePackages = "com.example.controller")
@MapperScan("com.example.mapper")		// 批量扫描Mapper，可以用来代替每个mapper接口类都要注解mapper的方式
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);	// 启动Spring应用，SpringApplication.run(springbootApplication注解的主程序类，args)
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return new com.alibaba.druid.pool.DruidDataSource();
	}
}