package ru.otus.gromov.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.gromov.domain.Author;

import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.service.AuthorService;
import ru.otus.gromov.service.BookService;
import ru.otus.gromov.service.GenreService;


import java.util.ArrayList;
import java.util.HashSet;
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
        authorService.insert(new Author(0L, name));
    }

    @ShellMethod(value = "Print all authors")
    public void printAllAuthors() {
        List<Author> list = authorService.getAll();
        System.out.println(list);
    }

    @ShellMethod(value = "Add new genre")
    public void addGenre(@ShellOption String name) {
        genreService.insert(new Genre(0L, name));
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
        bookService.insert(new Book(0L, name, new HashSet<>(), new HashSet<>(), new ArrayList<>()));
    }

    @ShellMethod(value = "Print book by name")
    public void printBookByName(@ShellOption String name) {
        System.out.println(bookService.getByName(name));

    }

    @ShellMethod(value = "Print book by ID")
    public void printBookById(@ShellOption Long id) {
        System.out.println(bookService.getById(id));
    }

    @ShellMethod(value = "Print book by ID")
    public void printCommentBookById(@ShellOption Long id) {
        System.out.println(bookService.getById(id));
    }

    @ShellMethod(value = "Add author to book")
    public void addAuthorToBook(@ShellOption Long id, @ShellOption String name) {
        Book book = bookService.getById(id);
        book.addAuthor(authorService.insert(new Author(0L, name)));
        bookService.insert(book);
    }

    @ShellMethod(value = "Add genre to book")
    public void addGenreToBook(@ShellOption Long id, @ShellOption String name) {
        Book book = bookService.getById(id);
        book.addGenre(genreService.insert(new Genre(0L, name)));
        bookService.insert(book);
    }

    @ShellMethod(value = "Add comment to book")
    public void addComment(@ShellOption Long id, @ShellOption String comment) {
        Book book = bookService.getById(id);
        book.addComment(new Comment(comment, book));
        bookService.insert(book);
    }

    @ShellMethod(value = "Delete book")
    public void deleteBook(@ShellOption Long id) {
        bookService.delete(id);
    }

    @ShellMethod(value = "Delete author")
    public void deleteAuthor(@ShellOption Long id) {
        authorService.delete(id);
    }

    @ShellMethod(value = "Print book by ID")
    public void deleteGenre(@ShellOption Long id) {
        genreService.delete(id);
    }
}
