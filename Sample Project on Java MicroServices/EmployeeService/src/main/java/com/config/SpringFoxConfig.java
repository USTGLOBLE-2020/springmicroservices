package com.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          //.apis(RequestHandlerSelectors.any()) //RequestHandlerSelectors.basePackage("com.*") 
          .apis(RequestHandlerSelectors.basePackage("com.controller")) 
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(medatadata());
    }
    
    private ApiInfo medatadata() {
    	ApiInfo api = new ApiInfo("API Documentation", "Employee CRUD operations", "1.0", "Microservices for employee CRUD operations", "Vijaya", 
    			"license", "http://localhost:9095/EmployeeAPI/getEmployeeList");
		return api;
	}
}
