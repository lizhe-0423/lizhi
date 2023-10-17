package com.lizhi;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 应用程序启动类
 *
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 */
@SpringBootApplication
@MapperScan("com.lizhi.mapper")
public class LizhiApplication {
	public static void main(String[] args) {
		SpringApplication.run(LizhiApplication.class, args);
	}
}
