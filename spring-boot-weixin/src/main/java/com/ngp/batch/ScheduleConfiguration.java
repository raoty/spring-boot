package com.ngp.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * 基于spring task的定时任务
 * 	spring test使用@Scheduled注释，相对于Quartz轻量，并且试用期更加方便
 * 
 * @author ahrty0814
 * @see https://juejin.im/post/5b31b9eff265da598826c200
 */

/**
 * 注册线程池
 * @author ahrty0814
 *
 */
@Configuration
public class ScheduleConfiguration implements SchedulingConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleConfiguration.class);
    
    private int taskNum = 2;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler());
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(taskNum);
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        taskScheduler.setThreadNamePrefix("schedule");				//线程名称前缀
        taskScheduler.setRemoveOnCancelPolicy(true);
        taskScheduler.setErrorHandler(t -> LOGGER.error("Error occurs", t));
        return taskScheduler;
    }
}
