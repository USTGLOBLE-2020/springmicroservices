package com.example.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableEurekaClient
@RestController
public class SpringConfigDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigDemoApplication.class, args);
	}
	  @RequestMapping(value = "/")
	   public String home() {
	      return "Spring Config Demo Application ";
	   }
}
