package com.rest.template;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class RestTemplateApplication {

	private static final Logger logger = Logger.getLogger(RestTemplateApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(RestTemplateApplication.class, args);

		logger.info("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			logger.info(beanName);
		}
	}

}