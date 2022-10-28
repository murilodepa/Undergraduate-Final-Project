/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc;

import com.api.tcc.faceRecognition.Training;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Murilo de Paula Araujo
 */
@EnableAsync
@SpringBootApplication
@RestController
public class TccApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TccApplication.class, args);
		//new Training();
	}
}
