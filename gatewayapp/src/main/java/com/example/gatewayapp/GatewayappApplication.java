package com.example.gatewayapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GatewayappApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayappApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r ->	r
						.path("/hello/**")
						.uri("http://localhost:9080")
						)
				.route(r ->	r
						.path("/customer/**")
						.uri("http://localhost:9080")
						)
				.build();
	}
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route(p -> p
	            .path("/get")
	            .filters(f -> f.addRequestHeader("Hello", "World"))
	            .uri("http://httpbin.org:80"))
	        .route(p -> p
	            .host("*.google.com")
	            .filters(f -> f.hystrix(config -> config.setName("mycmd")))
	            .uri("http://httpbin.org:80")).
	        build();
	}
}
