package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableConfigServer
public class MicroServicesDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicesDemoApplication.class, args);
	}

}
