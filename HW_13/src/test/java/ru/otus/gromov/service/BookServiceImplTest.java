package ru.otus.gromov.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.domain.Book;
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
        bookExpected = new Book(1L, "Effective java", "description",
                new HashSet<Genre>(Arrays.asList(
                        new Genre(1L, "Patterns"),
                        new Genre(2L, "Java"))),
                new HashSet<Author>(Collections.singletonList(
                        new Author(1L, "Joshua Bloch"))));
    }

    @Test
    public void count() {
        Assert.assertEquals(3, service.count());
    }

    @Test
    public void insert() {
        Book bookExpectedEdited = new Book(0L, "Effective java 3rd edition", "description",
                new HashSet<Genre>(Arrays.asList(
                        genreService.getById(1L),
                        genreService.getById(2L))),
                new HashSet<Author>(Collections.singletonList(
                        authorService.getById(1L))));
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
                new Book(1L, "Effective java", "description",
                        new HashSet<Genre>(Arrays.asList(
                                new Genre(1L, "Patterns"),
                                new Genre(2L, "Java"))),
                        new HashSet<Author>(Collections.singletonList(
                                new Author(1L, "Joshua Bloch")))),
                new Book(2L, "Java concurrency in practice", "description",
                        new HashSet<Genre>(Arrays.asList(
                                new Genre(2L, "Java"),
                                new Genre(3L, "Concurrency"))),
                        new HashSet<Author>(Arrays.asList(
                                new Author(1L, "Joshua Bloch"),
                                new Author(2L, "Brian Goetz"),
                                new Author(3L, "Doug Lea")))),
                new Book(3L, "Concurrent Programming in Java", "description",
                        new HashSet<Genre>(Arrays.asList(
                                new Genre(1L, "Patterns"),
                                new Genre(2L, "Java"))),
                        new HashSet<Author>(Collections.singletonList(
                                new Author(3L, "Doug Lea")))));

        Assert.assertEquals(ExpectedList, service.getAll());
    }

    @Test
    @Transactional()
    public void delete() {
        service.delete(bookExpected.getId());
        thrown.expect(NotFoundException.class);
        service.getById(bookExpected.getId());
    }

}