package ru.knastnt.tryrestconfig.utils;

import ru.knastnt.tryrestconfig.exceptions.MyResourceNotFoundException;

/**
 * простая утилита RestPreconditions в стиле Guava
 */
public class RestPreconditions {
    public static <T> T checkFound(T resource) {
        if (resource == null) {
            throw new MyResourceNotFoundException();
        }
        return resource;
    }
}
