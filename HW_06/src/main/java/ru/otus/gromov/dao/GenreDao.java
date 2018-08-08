package ru.otus.gromov.dao;

import ru.otus.gromov.domain.Genre;

import java.util.List;

public interface GenreDao {
    long count();

    Genre insert(Genre author);

    Genre getById(Long id);

    Genre getByName(String name);

    List<Genre> getAll();

    boolean delete(Genre genre);

}
