package ru.otus.gromov.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.gromov.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    long count();

    Genre insert(Genre genre);

    Optional<Genre> findById(String id);

    Optional<Genre> findByName(String name);

    List<Genre> findAll(Sort sort);

    void delete(Genre genre);

}
