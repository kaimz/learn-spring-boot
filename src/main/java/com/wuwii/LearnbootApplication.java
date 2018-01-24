package com.wuwii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class  LearnbootApplication {
	@RequestMapping(path = "/")
	public String index() {
		return "11";
	}

	public static void main(String[] args) {
		SpringApplication.run(LearnbootApplication.class, args);
	}
}
