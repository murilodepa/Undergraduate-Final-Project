package com.api.tcc;

import com.api.tcc.faceRecognition.Training;
import com.api.tcc.websocket.SocketClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@EnableAsync
@SpringBootApplication
@RestController
public class TccApplication {

	public static void main(String[] args) throws IOException {
		//SpringApplication.run(TccApplication.class, args);

		new Training();
	}

	@GetMapping("/")
	public String index(){
		return "Hello world!";
	}
}
