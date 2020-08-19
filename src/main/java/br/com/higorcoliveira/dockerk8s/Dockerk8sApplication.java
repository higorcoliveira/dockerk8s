package br.com.higorcoliveira.dockerk8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Dockerk8sApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dockerk8sApplication.class, args);
	}
}
