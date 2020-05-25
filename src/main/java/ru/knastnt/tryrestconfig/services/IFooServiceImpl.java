package ru.knastnt.tryrestconfig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.knastnt.tryrestconfig.entities.Foo;
import ru.knastnt.tryrestconfig.repositories.IFooRepository;

import java.util.Optional;

@Service
public class IFooServiceImpl implements IFooService {

    private IFooRepository fooRepository;

    @Autowired
    public IFooServiceImpl(IFooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Override
    public Optional<Foo> findById(Long id) {
        return fooRepository.findById(id);
    }

    @Override
    public Foo save(Foo foo) {
        return fooRepository.save(foo);
    }

    @Override
    public Iterable<Foo> findAll() {
        return fooRepository.findAll();
    }
}
