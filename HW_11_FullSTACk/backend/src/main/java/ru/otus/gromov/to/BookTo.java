package ru.otus.gromov.to;

import lombok.Data;

@Data
public class BookTo {
    String name;
    String description;
    String[] genres;
    String[] authors;
}
