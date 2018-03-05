package com.wuwii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author KronChan
 */
@SpringBootApplication
@EnableCaching
public class  LearnbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnbootApplication.class, args);
	}
}
