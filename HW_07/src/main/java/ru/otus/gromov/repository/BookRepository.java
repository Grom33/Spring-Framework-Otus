package ru.otus.gromov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.gromov.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    long count();

    Book save(Book book);

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    List<Book> findAll();

    void delete(Book book);

}
