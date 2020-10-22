package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableEurekaClient
public class MicroServicesDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicesDemoApplication.class, args);
	}

}
