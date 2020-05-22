package ru.knastnt.tryrestconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Самый простой способ начать использовать Spring Boot - создать основной класс и аннотировать его с помощью
 * @SpringBootApplication
 * Эта единственная аннотация эквивалентна использованию @Configuration, @EnableAutoConfiguration и @ComponentScan.
 * По умолчанию он будет сканировать все компоненты в одном пакете или ниже.
 */
@SpringBootApplication
public class TryRestConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(TryRestConfigApplication.class, args);
	}

}
