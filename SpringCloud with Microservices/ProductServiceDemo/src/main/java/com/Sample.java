package com;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@RequestMapping("/")
public class Sample {

	@RequestMapping("display")
	public String display() {
		return "Vijaya";
	}
}
