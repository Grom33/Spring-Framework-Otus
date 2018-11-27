package ru.otus.gromov.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.util.exception.EntityNotFound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Transactional
public class BookDaoJDBCTest extends AbstractDaoTest {
    @Autowired
    protected BookDao repository;

    private static Book bookExpected;

    @Before
    public void setUp() {
        this.bookExpected = new Book(1L, "Effective java",
                new HashSet<>(
                        Arrays.asList(
                                new Genre(1L, "Patterns"),
                                new Genre(2L, "Java"))),
                new HashSet<>(
                        Arrays.asList(
                                new Author(1L, "Joshua Bloch")
                        )
                ),
                new ArrayList<>());
    }

    @Test
    public void count() {
        Assert.assertEquals(3, repository.count());
    }

    @Test
    public void getById() {
        Book bookActual = repository.getById(bookExpected.getId());
        Assert.assertEquals(bookExpected, bookActual);
    }

    @Test
    public void insert() {
        Book bookExpectedEdited = new Book(0L, "Effective java 3rd edition",
                new HashSet<>(
                        Arrays.asList(
                                new Genre(1L, "Patterns"),
                                new Genre(2L, "Java"))),
                new HashSet<>(
                        Arrays.asList(
                                new Author(1L, "Joshua Bloch")
                        )),
                new ArrayList<>());
        repository.insert(bookExpectedEdited);
        System.out.println();
        Assert.assertEquals(bookExpectedEdited, repository.getByName(bookExpectedEdited.getName()));
    }

    @Test
    public void getAll() {
        List<Book> ExpectedList = new ArrayList<>(
                Arrays.asList(
                        new Book(1L, "Effective java",
                                new HashSet<>(
                                        Arrays.asList(
                                                new Genre(1L, "Patterns"),
                                                new Genre(2L, "Java"))),
                                new HashSet<>(
                                        Arrays.asList(
                                                new Author(1L, "Joshua Bloch")
                                        )),
                                new ArrayList<>()),
                        new Book(2L, "Java concurrency in practice",
                                new HashSet<>(
                                        Arrays.asList(
                                                new Genre(2L, "Java"),
                                                new Genre(3L, "Concurrency"))),
                                new HashSet<>(
                                        Arrays.asList(
                                                new Author(1L, "Joshua Bloch"),
                                                new Author(2L, "Brian Goetz"),
                                                new Author(3L, "Doug Lea")
                                        )),
                                new ArrayList<>()),
                        new Book(3L, "Concurrent Programming in Java",
                                new HashSet<>(
                                        Arrays.asList(
                                                new Genre(1L, "Patterns"),
                                                new Genre(2L, "Java"))),
                                new HashSet<>(
                                        Arrays.asList(
                                                new Author(3L, "Doug Lea")
                                        )),
                                new ArrayList<>())
                ));
        Assert.assertEquals(ExpectedList, repository.getAll());
    }

    @Test
    public void getByName() {
        Book bookActual = repository.getByName(bookExpected.getName());
        Assert.assertEquals(bookExpected, bookActual);
    }

    @Test
    public void delete() {
       repository.delete(bookExpected);
        thrown.expect(EntityNotFound.class);
        repository.getById(bookExpected.getId());
    }

    @Test
    public void addComment() {
        Book bookActual = repository.getByName(bookExpected.getName());
        bookActual.addComment(new Comment("test test", bookActual));
        repository.insert(bookActual);
        Assert.assertEquals(bookExpected, bookActual);
    }

}