package ru.knastnt.tryrestconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataGenerator implements CommandLineRunner {
    private CatRepository catRepository;
    private DogRepository dogRepository;
    private HorseRepository horseRepository;

    @Autowired
    public DataGenerator(CatRepository catRepository, DogRepository dogRepository, HorseRepository horseRepository) {
        this.catRepository = catRepository;
        this.dogRepository = dogRepository;
        this.horseRepository = horseRepository;
    }



    @Override
    public void run(String... args) throws Exception {
        dogRepository.saveAll(Arrays.asList(new Dog("чапи"), new Dog("bone")));
        horseRepository.saveAll(Arrays.asList(new Horse("минимус")));
    }
}
