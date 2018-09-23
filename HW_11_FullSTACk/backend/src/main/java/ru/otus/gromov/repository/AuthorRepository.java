package ru.otus.gromov.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Author;

public interface AuthorRepository extends ReactiveCrudRepository<Author, String> {
    Mono<Author> findByName(String name);

    Mono<Author> findById(String id);

}
