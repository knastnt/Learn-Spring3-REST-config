package ru.knastnt.tryrestconfig.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.knastnt.tryrestconfig.entities.Foo;

public interface IFooRepository extends PagingAndSortingRepository<Foo, Long> {
}
