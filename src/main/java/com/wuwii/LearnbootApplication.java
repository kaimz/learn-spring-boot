package com.wuwii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author server
 */
@SpringBootApplication
@RestController
public class  LearnbootApplication {

	@RequestMapping(path = "/")
	public String index() {
		System.out.println("12312");
		System.out.println("12312");
		System.out.println("123123");
		return "11";
	}


	public static void main(String[] args) {
		SpringApplication.run(LearnbootApplication.class, args);
	}
}
