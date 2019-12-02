package com.template.ie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@ImportResource(locations = {"classpath:applicationContext.xml"})
public class SpringcloudUserApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringcloudUserApplication.class, args);
	}
}