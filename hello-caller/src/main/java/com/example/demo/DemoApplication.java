package com.example.demo;

import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@RestController
public class DemoApplication {

	private WebClient webClient = WebClient.create();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// tag::caller-endpoint[]
	@GetMapping
	public Mono<String> index() {
		return webClient.get().uri("http://gs-spring-boot-k8s/name")
				.retrieve()
				.toEntity(String.class)
				.map(entity -> {
					String host = entity.getHeaders().get("k8s-host").get(0);
					return "Hello " + entity.getBody() + " from " + host;
				});

	}
	// end::caller-endpoint[]
}
