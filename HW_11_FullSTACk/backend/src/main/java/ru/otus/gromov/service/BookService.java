package ru.otus.gromov.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;

public interface BookService {
    Mono<Long> count();

    Mono<Book> save(Book book);

    Mono<Book> getById(String id);

    Mono<Book> getByName(String name);

    Flux<Book> getAll();

    Mono<Void> delete(String id);

    Mono<Void> addComment(String text, String bookId);

    Mono<Void> deleteComment(String commentId);

    Flux<Comment> getComments(String bookId);
}
