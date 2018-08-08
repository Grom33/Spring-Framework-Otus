package ru.otus.gromov.service;

import ru.otus.gromov.domain.Author;

import java.util.List;

public interface AuthorService {
    long count();

    Author insert(Author author);

    Author getById(Long id);

    List<Author> getAll();

    Author getByName(String name);

    void delete(Long id);
}
