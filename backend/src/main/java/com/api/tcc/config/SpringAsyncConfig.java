/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author Murilo de Paula Araujo
 */
public class SpringAsyncConfig {

    @Bean(name = "threadTrainingAndSendClient")
    public Executor threadTrainingAndSendClient()  {
        return new ThreadPoolTaskExecutor();
    }
}
