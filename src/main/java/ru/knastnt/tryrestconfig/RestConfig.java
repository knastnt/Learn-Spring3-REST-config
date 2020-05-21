package ru.knastnt.tryrestconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        ExposureConfiguration exposureConfiguration = config.getExposureConfiguration();

        //Убираем ненужные методы
        exposureConfiguration.withItemExposure((metadata, httpMethods) -> { //ЗАПРЕТ МЕТОДОВ
            return httpMethods.disable(HttpMethod.HEAD, HttpMethod.PUT, HttpMethod.OPTIONS);
        });

        //Доступны только репозитории, помеченные @ (Repository) RestResource, если только их exported флаг не установлен в false.
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);

    }
}