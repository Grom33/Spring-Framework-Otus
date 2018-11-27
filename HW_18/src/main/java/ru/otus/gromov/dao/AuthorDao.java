package ru.otus.gromov.dao;

import ru.otus.gromov.domain.Author;

import java.util.List;

public interface AuthorDao extends Repository {
    long count();

    Author insert(Author author);

    Author getById(Long id);

    Author getByName(String name);

    List<Author> getAll();

    void delete(Long id);
}
