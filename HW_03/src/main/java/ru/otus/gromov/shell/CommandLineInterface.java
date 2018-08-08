package ru.otus.gromov.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.gromov.domain.Author;

import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.service.AuthorService;
import ru.otus.gromov.service.BookService;
import ru.otus.gromov.service.GenreService;
import ru.otus.gromov.to.BoookWithPrintableFields;


import java.util.List;

@ShellComponent
public class CommandLineInterface {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public CommandLineInterface(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @ShellMethod(value = "Add new author")
    public void addAuthor(@ShellOption String name) {
        authorService.insert(new Author(name));
    }

    @ShellMethod(value = "Print all authors")
    public void printAllAuthors() {
        List<Author> list = authorService.getAll();
        System.out.println(list);
    }

    @ShellMethod(value = "Add new genre")
    public void addGenre(@ShellOption String name) {
        genreService.insert(new Genre(name));
    }

    @ShellMethod(value = "Print all genres")
    public void printAllGenres() {
        List<Genre> list = genreService.getAll();
        System.out.println(list);
    }

    @ShellMethod(value = "Print all book")
    public void printAllBook() {
        List<Book> list = bookService.getAll();
        System.out.println(list);
    }

    @ShellMethod(value = "Add new book")
    public void addBook(@ShellOption String name) {
        bookService.insert(new Book(name));
    }

    @ShellMethod(value = "Print book by name")
    public void printBookByName(@ShellOption String name) {
        System.out.println(bookService.getPrintableBook(bookService.getByName(name)));

    }

    @ShellMethod(value = "Print book by ID")
    public void printBookById(@ShellOption int id) {
        System.out.println(bookService.getPrintableBook(bookService.getById(id)));
    }

    @ShellMethod(value = "Add author to book")
    public void addAuthorToBook(@ShellOption int id, @ShellOption String name) {
        Book book = bookService.getById(id);
        book.addAuthor(authorService.insert(new Author(name)).getId());
        bookService.insert(book);
    }

    @ShellMethod(value = "Add genre to book")
    public void addGenreToBook(@ShellOption int id, @ShellOption String name) {
        Book book = bookService.getById(id);
        book.addAuthor(genreService.insert(new Genre(name)).getId());
        bookService.insert(book);
    }

    @ShellMethod(value = "Delete book")
    public void deleteBook(@ShellOption int id) {
        bookService.delete(id);
    }

    @ShellMethod(value = "Delete author")
    public void deleteAuthor(@ShellOption int id) {
        authorService.delete(id);
    }

    @ShellMethod(value = "Print book by ID")
    public void deleteGenre(@ShellOption int id) {
        genreService.delete(id);
    }


}
