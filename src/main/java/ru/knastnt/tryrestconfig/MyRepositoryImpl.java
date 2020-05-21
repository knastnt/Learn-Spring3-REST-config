package ru.knastnt.tryrestconfig;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class MyRepositoryImpl <T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {
    private EntityManager entityManager;

//    public MyRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
//        super(entityInformation, entityManager);
//    }

    // There are two constructors to choose from, either can be used.
    public MyRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);

        // This is the recommended method for accessing inherited class dependencies.
        this.entityManager = entityManager;
    }

    public void sharedCustomMethod(ID id) {
        // implementation goes here
    }
}
