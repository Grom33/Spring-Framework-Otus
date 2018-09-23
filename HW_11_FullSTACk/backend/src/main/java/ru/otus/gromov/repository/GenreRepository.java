package ru.otus.gromov.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Genre;

public interface GenreRepository extends ReactiveCrudRepository<Genre, String> {

    Mono<Genre> findByName(String name);

    Mono<Genre> findById(String id);

}
