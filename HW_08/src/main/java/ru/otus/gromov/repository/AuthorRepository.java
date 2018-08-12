package ru.otus.gromov.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.gromov.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String name);
}
