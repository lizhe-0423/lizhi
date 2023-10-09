package com.lizhi.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@description MyBatis Plus 配置
 *@author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *@date 2023/10/7
 **/

@Configuration
@MapperScan("com.lizhi.mapper")
public class MyBatisPlusConfig {

    /**
     * 拦截器配置
     *
     * @return interceptor插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}