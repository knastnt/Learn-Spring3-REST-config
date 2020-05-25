package ru.knastnt.tryrestconfig.services;

import ru.knastnt.tryrestconfig.entities.Foo;

import java.util.Optional;

public interface IFooService {
    Optional<Foo> findById(Long id);

    Foo save(Foo foo);

    Iterable<Foo> findAll();
}
