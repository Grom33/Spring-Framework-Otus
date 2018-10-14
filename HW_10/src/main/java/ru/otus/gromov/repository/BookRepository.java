package ru.otus.gromov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.gromov.domain.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);

}
