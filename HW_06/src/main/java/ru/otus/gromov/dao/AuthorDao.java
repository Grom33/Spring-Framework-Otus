package ru.otus.gromov.dao;

import ru.otus.gromov.domain.Author;

import java.util.List;

public interface AuthorDao {
    long count();

    Author insert(Author author);

    Author getById(Long id);

    Author getByName(String name);

    List<Author> getAll();

    boolean delete(Author author);
}
