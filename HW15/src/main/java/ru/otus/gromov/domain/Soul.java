package ru.otus.gromov.domain;

import lombok.Data;

@Data
public class Soul {
    String name;
    int sins;

    public Soul(String name, int sins) {
        this.name = name;
        this.sins = sins;
    }
}

