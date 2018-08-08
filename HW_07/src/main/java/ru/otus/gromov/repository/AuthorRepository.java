package ru.otus.gromov.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.gromov.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    long count();

    Author save(Author author);

    Optional<Author> findById(Long id);

    Optional<Author> findByName(String name);

    List<Author> findAll(Sort sort);

    void delete(Author author);
}
