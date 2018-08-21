package ru.otus.gromov.web.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.service.AuthorService;
import ru.otus.gromov.service.BookService;
import ru.otus.gromov.service.GenreService;
import ru.otus.gromov.to.BookTo;

import java.util.List;

import static ru.otus.gromov.util.EntityUtil.bookToToBook;

@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/books")
    public String listBook(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "book/books";
    }

    @GetMapping("/book/{id}")
    public String getByid(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        model.addAttribute("Comments", bookService.getComments(id));
        return "book/book";
    }

    @GetMapping("/book/edit/{id}")
    public String previewBook(@PathVariable Long id, Model model) {
        if (id == 0) {
            model.addAttribute("book", new Book());
        } else {
            model.addAttribute("book", bookService.getById(id));
        }

        model.addAttribute("genres", genreService.getAll());
        model.addAttribute("authors", authorService.getAll());
        return "book/edit_book";
    }

    @PostMapping("/book/edit/{id}")
    public String editBook(@RequestParam("name") String name,
                           @RequestParam("description") String desc,
                           @RequestParam("authors") Long[] authors,
                           @RequestParam("genres") Long[] genres,
                           @PathVariable Long id,
                           Model model) {

        Book book = new Book();
        if (id != 0) book = bookService.getById(id);

        book = bookToToBook(new BookTo(name, desc, genres, authors),
                book,
                authorService,
                genreService);
        bookService.save(book);
        model.addAttribute("book", book);
        model.addAttribute("genres", genreService.getAll());
        model.addAttribute("authors", authorService.getAll());
        return "book/book";
    }

    @PostMapping("/comment/add/{id}")
    public String addComment(@RequestParam("review") String review,
                             @PathVariable Long id, Model model) {
        bookService.addComment(review, id);
        model.addAttribute("book", bookService.getById(id));
        model.addAttribute("Comments", bookService.getComments(id));
        return "book/book";
    }

}
