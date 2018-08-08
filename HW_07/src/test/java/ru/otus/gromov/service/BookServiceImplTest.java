package ru.otus.gromov.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.*;

@Transactional
public class BookServiceImplTest extends AbstractServiceTest {
    @Autowired
    protected BookService service;
    @Autowired
    protected AuthorService authorService;
    @Autowired
    protected GenreService genreService;

    private static Book bookExpected;

    @Before
    public void setUp() {
        bookExpected = new Book(1L, "Effective java",
                new HashSet<>(Arrays.asList(
                        new Genre(1L, "Patterns"),
                        new Genre(2L, "Java"))),
                new HashSet<>(Collections.singletonList(
                        new Author(1L, "Joshua Bloch"))),
                new ArrayList<>());
    }

    @Test
    public void count() {
        Assert.assertEquals(3, service.count());
    }

    @Test
    public void insert() {
        Book bookExpectedEdited = new Book(0L, "Effective java 3rd edition",
                new HashSet<>(Arrays.asList(
                        genreService.getById(1L),
                        genreService.getById(2L))),
                new HashSet<>(Collections.singletonList(
                        authorService.getById(1L))),
                new ArrayList<>());
        service.save(bookExpectedEdited);
        Assert.assertEquals(bookExpectedEdited, service.getByName(bookExpectedEdited.getName()));
    }

    @Test
    public void getById() {
        Assert.assertEquals(bookExpected, service.getById(bookExpected.getId()));
    }

    @Test
    public void getByName() {
        Assert.assertEquals(
                bookExpected,
                service.getByName(bookExpected.getName()));
    }

    @Test
    public void getAll() {
        List<Book> ExpectedList = Arrays.asList(
                new Book(1L, "Effective java",
                        new HashSet<>(Arrays.asList(
                                new Genre(1L, "Patterns"),
                                new Genre(2L, "Java"))),
                        new HashSet<>(Collections.singletonList(
                                new Author(1L, "Joshua Bloch"))),
                        new ArrayList<>()),
                new Book(2L, "Java concurrency in practice",
                        new HashSet<>(Arrays.asList(
                                new Genre(2L, "Java"),
                                new Genre(3L, "Concurrency"))),
                        new HashSet<>(Arrays.asList(
                                new Author(1L, "Joshua Bloch"),
                                new Author(2L, "Brian Goetz"),
                                new Author(3L, "Doug Lea"))),
                        new ArrayList<>()),
                new Book(3L, "Concurrent Programming in Java",
                        new HashSet<>(Arrays.asList(
                                new Genre(1L, "Patterns"),
                                new Genre(2L, "Java"))),
                        new HashSet<>(Collections.singletonList(
                                new Author(3L, "Doug Lea"))),
                        new ArrayList<>()));

        Assert.assertEquals(ExpectedList, service.getAll());
    }

    @Test
    @Transactional()
    public void delete() {
        service.delete(bookExpected.getId());
        thrown.expect(NotFoundException.class);
        service.getById(bookExpected.getId());
    }

    @Test
    public void addComment() {
        Book bookActual = service.getByName(bookExpected.getName());
        bookActual.addComment(new Comment("test 1", bookActual));
        bookActual.addComment(new Comment("test 2", bookActual));
        bookActual.addComment(new Comment("test 3", bookActual));
        service.save(bookActual);
        Assert.assertEquals(bookExpected, bookActual);
    }
}