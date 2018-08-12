package ru.otus.gromov.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.gromov.domain.Author;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {

    long count();

    Author insert(Author author);

    Optional<Author> findById(String id);

    Optional<Author> findByName(String name);

    List<Author> findAll(Sort sort);

    void delete(Author author);
}
