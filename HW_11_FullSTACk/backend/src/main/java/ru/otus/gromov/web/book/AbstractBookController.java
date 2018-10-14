package ru.otus.gromov.web.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;
import ru.otus.gromov.service.BookService;


@RestController
public abstract class AbstractBookController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookService bookService;

    public Mono<Book> create(Book book) {
        log.info("create book {}", book.getName());
        return bookService.save(book);
    }

    public Mono<Book> get(String id) {
        log.info("get book {}", id);
        return bookService.getById(id);
    }

    public Mono<Book> update(Book book) {
        log.info("update book {}", book.getId());
        return bookService.save(book);
    }

    public Mono<Void> delete(String id) {
        log.info("delete book {}", id);
        return bookService.delete(id);
    }

    public Flux<Book> getAll() {
        log.info("get all books");
        return bookService.getAll();
    }

    public Mono<Void> addComment(String comment, String id) {
        log.info("add comment to book");
        return bookService.addComment(comment, id);
    }

    public Flux<Comment> getComments(String id) {
        log.info("get book comments");
        return bookService.getComments(id);
    }

}
