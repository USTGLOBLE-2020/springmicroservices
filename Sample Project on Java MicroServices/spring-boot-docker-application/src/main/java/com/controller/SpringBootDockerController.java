package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/docker")
public class SpringBootDockerController {

	@GetMapping
	public String index() {
		return "Docker implementation";
	}
}
