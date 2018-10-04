package ru.otus.gromov.web.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;

import java.util.List;

@RestController
public class BookRestController extends AbstractBookController {

    @GetMapping(value = "/api/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book get(@PathVariable Long id) {
        return super.get(id);
    }

    @DeleteMapping("/api/books/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }

    @PostMapping(value = "/api/books/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book create(@RequestBody Book book) {
        return super.create(book);
    }

    @PutMapping(value = "/api/books/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Book book) {
        super.update(book);
    }

    @GetMapping(value = "/api/books/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAll() {
        return super.getAll();
    }

    @PostMapping(value = "/api/books/{id}/comments/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addComment(@RequestBody Comment comment, @PathVariable Long id) {
        super.addComment(comment.getReview(), id);
    }

    @GetMapping(value = "/api/books/{id}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> getComments(@PathVariable Long id) {
        return super.getComments(id);
    }

}
