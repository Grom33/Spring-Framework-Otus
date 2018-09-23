package ru.otus.gromov.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Book;

public interface BookRepository extends ReactiveCrudRepository<Book, String> {
    Mono<Book> findByName(String name);

    Mono<Book> findById(String id);
}
