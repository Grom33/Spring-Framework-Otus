package ru.otus.gromov.web.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;

@RestController
public class BookRestController extends AbstractBookController {

    @GetMapping(value = "/api/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Book> get(@PathVariable String id) {
        return super.get(id);
    }

    @DeleteMapping("/api/books/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return super.delete(id);
    }

    @PostMapping(value = "/api/books/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Book> create(@RequestBody Book book) {
        return super.create(book);
    }

    @PutMapping(value = "/api/books/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Book> update(@RequestBody Book book) {
        return super.update(book);
    }

    @GetMapping(value = "/api/books/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Book> getAll() {
        return super.getAll();
    }

    @PostMapping(value = "/api/books/{id}/comments/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> addComment(@RequestBody Comment comment, @PathVariable String id) {
        return super.addComment(comment.getReview(), id);
    }

    @GetMapping(value = "/api/books/{id}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Comment> getComments(@PathVariable String id) {
        return super.getComments(id);
    }

}
