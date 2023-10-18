package com.lizhi.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池配置
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 */
@Configuration
public class ThreadPoolExecutorConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        ThreadFactory threadFactory=new ThreadFactory() {
            int count = 1;
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread=new Thread(r);

                thread.setName("线程"+ count);
                count++;
                return thread;
            }
        };
        return new ThreadPoolExecutor(2,4,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),threadFactory);
    }
}
