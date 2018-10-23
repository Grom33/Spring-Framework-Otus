package ru.otus.gromov.service;

import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;

import java.util.List;

public interface BookService {
    long count();

    Book save(Book book);

    Book getById(Long id);

    Book getByName(String name);

    List<Book> getAll();

    void delete(Long id);

    void addComment(String text, Long bookId);

    void deleteComment(Long commentId);

    List<Comment> getComments(Long bookId);

}
