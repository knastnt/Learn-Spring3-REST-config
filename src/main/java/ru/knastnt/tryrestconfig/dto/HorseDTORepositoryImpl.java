package ru.knastnt.tryrestconfig.dto;

import org.springframework.beans.factory.annotation.Autowired;
import ru.knastnt.tryrestconfig.Horse;
import ru.knastnt.tryrestconfig.HorseRepository;
import ru.knastnt.tryrestconfig.dto.HorseDTORepository;

import java.util.HashSet;
import java.util.Set;

public class HorseDTORepositoryImpl implements HorseDTORepository {
    private HorseRepository horseRepository;

    @Autowired
    public HorseDTORepositoryImpl(HorseRepository horseRepository) {
        this.horseRepository = horseRepository;
    }

    @Override
    public Iterable<HorseDTO> findAll() {
        Set<HorseDTO> toReturn = new HashSet<>();
        horseRepository.findAll().forEach(horse -> toReturn.add(new HorseDTO(horse)));
        return toReturn;
    }
}
