package ru.otus.gromov.dao;

import ru.otus.gromov.domain.Book;

import java.util.List;

public interface BookDao {

    long count();

    Book insert(Book book);

    Book getById(Long id);

    Book getByName(String name);

    List<Book> getAll();

    boolean delete(Book book);

}
