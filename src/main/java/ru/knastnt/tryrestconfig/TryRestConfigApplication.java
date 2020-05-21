package ru.knastnt.tryrestconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class TryRestConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(TryRestConfigApplication.class, args);
	}

}
