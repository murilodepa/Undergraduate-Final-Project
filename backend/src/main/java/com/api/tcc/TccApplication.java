package com.api.tcc;

import com.api.tcc.Utils.ManipulatingImage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@RestController
public class TccApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TccApplication.class, args);
	/*	ManipulatingImage manipulatingImage = new ManipulatingImage();
		String encode = manipulatingImage.encodeImage(true);
		System.out.println(encode);
		manipulatingImage.decodeImage(encode, true);*/
	}

	@GetMapping("/")
	public String index(){
		return "hello world!";
	}
}
