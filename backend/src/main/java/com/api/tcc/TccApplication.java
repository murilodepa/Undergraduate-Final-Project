package com.api.tcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TccApplication {

	public static void main(String[] args) {
		SpringApplication.run(TccApplication.class, args);

		/*
		try {
			new socketClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/

	}

	@GetMapping("/")
	public String index(){
		return "Hello world!";
	}
}
