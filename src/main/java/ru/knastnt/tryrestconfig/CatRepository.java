package ru.knastnt.tryrestconfig;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CatRepository  extends Repository<Cat, Long> {
    Iterable<Cat> findAll();
}
