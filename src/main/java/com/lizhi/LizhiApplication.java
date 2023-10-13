package com.lizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * 应用程序启动类
 *
 * @author <a href="https://github.com/lizhe-0423">lizhi</a>
 */
@SpringBootApplication
@MapperScan("com.lizhi.mapper")
@EnableAsync
public class LizhiApplication {
	public static void main(String[] args) {
		SpringApplication.run(LizhiApplication.class, args);
	}
}
