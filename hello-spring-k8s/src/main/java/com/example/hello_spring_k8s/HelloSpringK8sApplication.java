package com.example.hello_spring_k8s;

import java.util.Random;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloSpringK8sApplication {

	@Autowired
	private Environment env;

	public static final String[] NAMES = new String[] {"Paul", "John", "Ringo", "George"};
	private static final Random r = new Random();

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringK8sApplication.class, args);
	}

	@GetMapping("helloWorld")
	public String helloWorld() {
		return "Hello World!!";
	}

	@GetMapping("name")
	public String getName(HttpServletResponse response) {
		response.addHeader("k8s-host", env.getProperty("HOSTNAME"));
		return NAMES[r.ints(0, NAMES.length).limit(1).findFirst().getAsInt()];
	}

}
