package ru.knastnt.tryrestconfig;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DogRepository extends CrudRepository<Dog, Long> {
}
