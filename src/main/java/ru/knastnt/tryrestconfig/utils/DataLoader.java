package ru.knastnt.tryrestconfig.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.knastnt.tryrestconfig.entities.Foo;
import ru.knastnt.tryrestconfig.repositories.IFooRepository;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    private IFooRepository fooRepository;

    public DataLoader(IFooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Foo f1 = new Foo();
        Foo f2 = new Foo();
        Foo f3 = new Foo();

        f1.setId(1L);
        f1.setName("один");
        f2.setId(2L);
        f2.setName("два");
        f3.setId(3L);
        f3.setName("три");

        fooRepository.saveAll(Arrays.asList(f1, f2, f3));
    }
}
