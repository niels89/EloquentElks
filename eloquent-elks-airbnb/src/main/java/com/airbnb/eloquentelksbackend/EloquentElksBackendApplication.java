package com.airbnb.eloquentelksbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@RestController
public class EloquentElksBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EloquentElksBackendApplication.class, args);
	}

//	@GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	@ResponseBody
//	public String helloWorld() {
//		return "The application is running.";
//	}

}
