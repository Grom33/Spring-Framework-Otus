package ru.otus.gromov.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;
import ru.otus.gromov.service.BookService;

import java.util.List;

@RestController
public class BookRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/api/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book get(@PathVariable Long id) {
        log.info("get book {}", id);
        return bookService.getById(id);
    }

    @DeleteMapping("/api/books/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("delete book {}", id);
        bookService.delete(id);
    }

    @PostMapping(value = "/api/books/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book create(@RequestBody Book book) {
        log.info("create book {}", book.getName());
        return bookService.save(book);
    }

    @PutMapping(value = "/api/books/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Book book) {
        log.info("update book {}", book.getId());
        bookService.save(book);
    }

    @GetMapping(value = "/api/books/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAll() {
        log.info("get all books");
        return bookService.getAll();
    }

    @PostMapping(value = "/api/books/{id}/comments/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addComment(@RequestBody Comment comment, @PathVariable Long id) {
        log.info("add comment to book");
        bookService.addComment(comment.getReview(), id);
    }

    @GetMapping(value = "/api/books/{id}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> getComments(@PathVariable Long id) {
        log.info("get book comments");
        return bookService.getComments(id);
    }

}
