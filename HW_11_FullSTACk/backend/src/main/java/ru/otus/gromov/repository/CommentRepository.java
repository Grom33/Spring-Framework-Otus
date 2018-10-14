package ru.otus.gromov.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Comment;

public interface CommentRepository extends ReactiveCrudRepository<Comment, String> {

    Flux<Comment> findAllByBookId(String bookId);

    Mono<Comment> findByReview(String review);

    Mono<Comment> findById(String id);
}
