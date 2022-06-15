package com.api.tcc.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

public class SpringAsyncConfig {

    @Bean(name = "threadTrainingAndSendClient")
    public Executor threadTrainingAndSendClient()  {
        return new ThreadPoolTaskExecutor();
    }
}
