package com.wujiaqi.aopdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wujiaqi
 */
@Configuration
@Slf4j
public class ThreadPoolConfig {


    private final int num = Runtime.getRuntime().availableProcessors();


    @Bean("threadPool")
    public Executor getThreadPoolExecutor() {
        log.warn(">>>>>>>>>>thread pool init start cpuNum {} <<<<<<<<<<", num);
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

        threadPoolTaskExecutor.setCorePoolSize(num);
        threadPoolTaskExecutor.setMaxPoolSize(num * 2);
        threadPoolTaskExecutor.setKeepAliveSeconds(50);
        threadPoolTaskExecutor.setQueueCapacity(100);
        threadPoolTaskExecutor.setThreadNamePrefix("bus-thread-pool");
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        //等待任务在关机时完成--表名等待所有线程执行完
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(false);

        //抛出-让调用的线程来执行该任务
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolTaskExecutor.initialize();
        log.warn(">>>>>>>>>>thread pool init success<<<<<<<<<<");
        return threadPoolTaskExecutor;
    }

}
