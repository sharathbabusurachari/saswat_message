package com.telspiel.saswat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"com.telspiel.saswat"})
public class SaswatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaswatApplication.class, args);
	}

}
