package com.example.demowar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demowar.configuration.CorsFilter;

@SpringBootApplication
public class DemowarApplication {
	
	private static Logger logger = LoggerFactory.getLogger(CorsFilter.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DemowarApplication.class, args);

	}
	
	

	

}
