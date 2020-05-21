package ru.knastnt.tryrestconfig;

import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends MyRepository<Dog, Long> {
}
