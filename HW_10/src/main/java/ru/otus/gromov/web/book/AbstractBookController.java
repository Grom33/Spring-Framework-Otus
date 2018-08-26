package ru.otus.gromov.web.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;
import ru.otus.gromov.service.BookService;

import java.util.List;

@RestController
public abstract class AbstractBookController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookService bookService;

    public Book create(Book book) {
        log.info("create book {}", book.getName());
        return bookService.save(book);
    }

    public Book get(Long id) {
        log.info("get book {}", id);
        return bookService.getById(id);
    }

    public void update(Book book) {
        log.info("update book {}", book.getId());
        bookService.save(book);
    }

    public void delete(Long id) {
        log.info("delete book {}", id);
        bookService.delete(id);
    }

    public List<Book> getAll() {
        log.info("get all books");
        return bookService.getAll();
    }

    public void addComment(String comment, Long id) {
        log.info("add comment to book");
        bookService.addComment(comment, id);
    }

    public List<Comment> getComments(Long id) {
        log.info("get book comments");
        return bookService.getComments(id);
    }

}
