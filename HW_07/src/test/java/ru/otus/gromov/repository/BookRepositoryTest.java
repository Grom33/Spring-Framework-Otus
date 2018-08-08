package ru.otus.gromov.repository;

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
public class BookRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    protected BookRepository repository;
    @Autowired
    protected GenreRepository genreRepository;
    @Autowired
    protected AuthorRepository authorRepository;

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
        Assert.assertEquals(3, repository.count());
    }

    @Test
    public void findById() {
        Book bookActual = repository.findById(bookExpected.getId()).orElseThrow(() -> new NotFoundException("Book not found!"));
        Assert.assertEquals(bookExpected, bookActual);
    }

    @Test
    public void save() {
        Book bookExpectedEdited = new Book(0L, "Effective java 3rd edition",
                new HashSet<>(Arrays.asList(
                        genreRepository.findById(1L).orElseThrow(() -> new NotFoundException("Genre not found!")),
                        genreRepository.findById(2L).orElseThrow(() -> new NotFoundException("Genre not found!")))),
                new HashSet<>(Collections.singletonList(
                        authorRepository.findById(1L).orElseThrow(() -> new NotFoundException("Author not found!")))),
                new ArrayList<>());
        repository.save(bookExpectedEdited);

        Assert.assertEquals(
                bookExpectedEdited,
                repository.findByName(bookExpectedEdited.getName())
                        .orElseThrow(() -> new NotFoundException("Book not found!")));
    }

    @Test
    public void findAll() {
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
        Assert.assertEquals(ExpectedList, repository.findAll());
    }

    @Test
    public void findByName() {
        Book bookActual = repository.findByName(bookExpected.getName()).orElseThrow(() -> new NotFoundException("Book not found!"));
        Assert.assertEquals(bookExpected, bookActual);
    }

    @Test
    public void delete() {
        repository.delete(repository.findById(bookExpected.getId()).orElseThrow(() -> new NotFoundException("Book not found!")));

        thrown.expect(NotFoundException.class);
        repository.findById(bookExpected.getId()).orElseThrow(() -> new NotFoundException("Book not found!"));
    }

    @Test
    public void addComment() {
        Book bookActual = repository.findByName(bookExpected.getName()).orElseThrow(() -> new NotFoundException("Book not found!"));
        bookActual.addComment(new Comment("test test", bookActual));

        repository.save(bookActual);
        Assert.assertEquals(bookExpected, bookActual);
    }
}