package ru.knastnt.tryrestconfig.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration

/**
 * аннотация @EnableWebMvc делает несколько полезных вещей - в частности, в случае REST она обнаруживает
 * существование Jackson и JAXB 2 на пути к классам и автоматически создает и регистрирует конвертеры
 * JSON и XML по умолчанию. Это быстрый путь, и хотя он может быть полезен во многих ситуациях, он не
 * идеален. Когда требуется более сложная конфигурация, удалите аннотацию
 * и расширьте WebMvcConfigurationSupport напрямую.
 *
 *
 * Если мы используем аннотацию @SpringBootApplication, а библиотека spring-webmvc находится в пути к классам,
 * то аннотация @EnableWebMvc добавляется автоматически с автоконфигурацией по умолчанию. Мы все еще можем добавить
 * функциональность MVC в эту конфигурацию, реализовав интерфейс WebMvcConfigurer в аннотированном
 * классе @Configuration. Мы также можем использовать экземпляр WebMvcRegistrationsAdapter для предоставления
 * наших собственных реализаций RequestMappingHandlerMapping, RequestMappingHandlerAdapter
 * или ExceptionHandlerExceptionResolver. Наконец, если мы хотим отказаться от функций Spring Boot MVC
 * и объявить пользовательскую конфигурацию, мы можем сделать это с помощью аннотации @EnableWebMvc.
 */
@EnableWebMvc
public class WebConfig{
    //
}