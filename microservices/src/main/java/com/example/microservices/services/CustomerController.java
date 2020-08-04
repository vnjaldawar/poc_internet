package com.example.microservices.services;

import java.time.Instant;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@PostMapping("/add/{name}")
	public String addCustomer(@PathVariable String name) {
		return "Hello , " + name + ", " + Instant.now();
	}
}
