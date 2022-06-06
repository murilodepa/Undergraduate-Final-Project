package com.api.tcc;

import com.api.tcc.websocket.SocketClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootApplication
@RestController
public class TccApplication {

	public static void main(String[] args) {
		SpringApplication.run(TccApplication.class, args);

/*
		try {
			new SocketClient();
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
