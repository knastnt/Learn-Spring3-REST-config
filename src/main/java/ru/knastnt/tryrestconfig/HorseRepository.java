package ru.knastnt.tryrestconfig;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//Не рест ресурс, а просто репозиторий
public interface HorseRepository extends CrudRepository<Horse, Long> {
}
