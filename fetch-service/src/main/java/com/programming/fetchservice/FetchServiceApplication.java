package com.programming.fetchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FetchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FetchServiceApplication.class, args);
	}

}
