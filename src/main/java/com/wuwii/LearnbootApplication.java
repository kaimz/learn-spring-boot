package com.wuwii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author KronChan
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class  LearnbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnbootApplication.class, args);
	}
}
