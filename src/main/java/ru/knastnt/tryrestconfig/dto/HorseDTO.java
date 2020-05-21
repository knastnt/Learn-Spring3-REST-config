package ru.knastnt.tryrestconfig.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.server.core.Relation;
import ru.knastnt.tryrestconfig.Horse;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Entity //А вот этого надо избежать
@Data
public class HorseDTO {
    @Id
    private long id;
    private String name;
    private int age;
    private boolean isMale;

    public HorseDTO(Horse horse) {
        id = horse.getId();
        name = horse.getName();
        age = horse.getAge();
        isMale = horse.isMale();
    }
}
