package ru.knastnt.tryrestconfig.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Foo {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
