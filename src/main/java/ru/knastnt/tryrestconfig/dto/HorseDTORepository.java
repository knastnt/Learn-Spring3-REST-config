package ru.knastnt.tryrestconfig.dto;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.knastnt.tryrestconfig.dto.HorseDTO;

@RepositoryRestResource
public interface HorseDTORepository extends Repository<HorseDTO, Long> {
    Iterable<HorseDTO> findAll();
}
