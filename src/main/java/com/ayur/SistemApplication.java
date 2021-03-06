package com.ayur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SistemApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SistemApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	    // TODO Auto-generated method stub
	    System.setProperty("spring.config.name", "application");
	    return builder.sources(SistemApplication.class);
	}
	
	
}
