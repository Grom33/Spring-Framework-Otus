package ru.otus.gromov.dao;

import ru.otus.gromov.domain.Book;

import java.util.List;

public interface BookDao {

    int count();

    boolean insert(Book book);

    boolean update(Book book);

    Book getById(int id);

    Book getByName(String name);

    List<Book> getAll();

    boolean delete(int id);

}
