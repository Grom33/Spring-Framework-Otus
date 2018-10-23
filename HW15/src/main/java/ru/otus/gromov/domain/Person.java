package ru.otus.gromov.domain;

import lombok.Data;

@Data
public class Person {
    Soul soul;

    public Person(Soul soul) {
        this.soul = soul;
    }
}
