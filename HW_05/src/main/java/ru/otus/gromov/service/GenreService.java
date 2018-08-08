package ru.otus.gromov.service;

import ru.otus.gromov.domain.Genre;

import java.util.List;

public interface GenreService {
    int count();

    Genre insert(Genre author);

    Genre getById(int id);

    Genre getByName(String name);

    List<Genre> getAll();

    void delete(int id);

    List<Genre> getbyArrayID(Integer[] arrayOfId);
}
