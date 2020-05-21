package ru.knastnt.tryrestconfig;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Arrays;

public class CatRepositoryImpl implements CatRepository {


    @Override
    public Iterable<Cat> findAll() {
        return Arrays.asList(new Cat("жорик", 4), new Cat("Данила", 7));
    }
}
