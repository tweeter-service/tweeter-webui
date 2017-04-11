package com.example;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TweeterWebuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweeterWebuiApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(HttpServletRequest req) {
		return new RestTemplateBuilder().interceptors((request, body, execution) -> {
			String authorization = req.getHeader(HttpHeaders.AUTHORIZATION);
			// pass through basic authorization
			request.getHeaders().set(HttpHeaders.AUTHORIZATION, authorization);
			return execution.execute(request, body);
		}).build();
	}
}
