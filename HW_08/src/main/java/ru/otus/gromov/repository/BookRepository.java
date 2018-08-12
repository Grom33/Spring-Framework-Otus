package ru.otus.gromov.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.gromov.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    long count();

    Book insert(Book book);

    Optional<Book> findById(String id);

    Optional<Book> findByName(String name);

    List<Book> findAll(Sort sort);

    void delete(Book book);
}
