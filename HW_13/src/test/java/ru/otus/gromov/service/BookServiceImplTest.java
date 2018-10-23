package ru.otus.gromov.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.*;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners(listeners = {ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Transactional
public class BookServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

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

    @WithMockUser(
            username = "Admin",
            authorities = "ROLE_ADMIN"
    )
    @Test
    public void insert() {
        Book bookExpectedEdited = new Book(0L, "Effective java 3rd edition", "description",
                new HashSet<Genre>(Arrays.asList(
                        genreService.getById(1L),
                        genreService.getById(2L))),
                new HashSet<Author>(Collections.singletonList(
                        authorService.getById(1L))));
        service.save(bookExpectedEdited);
        //Assert.assertEquals(bookExpectedEdited, service.getByName(bookExpectedEdited.getName()));
    }

    @WithMockUser(
            username = "admin",
            authorities = "ROLE_ADMIN"
    )
    @Test
    public void getById() {
        Assert.assertEquals(bookExpected, service.getById(bookExpected.getId()));
    }

    @WithMockUser(
            username = "Admin",
            authorities = "ROLE_ADMIN"
    )
    @Test
    public void getByName() {
        Assert.assertEquals(
                bookExpected,
                service.getByName(bookExpected.getName()));
    }

    @WithMockUser(
            username = "Admin",
            authorities = "ROLE_ADMIN"
    )
    @Test
    public void getAllWithAdminAuth() {
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

    @WithMockUser(
            username = "user",
            authorities = "ROLE_USER"
    )
    @Test
    public void getAllWithUserAuth() {
        List<Book> ExpectedList = Arrays.asList(
                new Book(1L, "Effective java", "description",
                        new HashSet<Genre>(Arrays.asList(
                                new Genre(1L, "Patterns"),
                                new Genre(2L, "Java"))),
                        new HashSet<Author>(Collections.singletonList(
                                new Author(1L, "Joshua Bloch")))),
                new Book(3L, "Concurrent Programming in Java", "description",
                        new HashSet<Genre>(Arrays.asList(
                                new Genre(1L, "Patterns"),
                                new Genre(2L, "Java"))),
                        new HashSet<Author>(Collections.singletonList(
                                new Author(3L, "Doug Lea")))));

        Assert.assertEquals(ExpectedList, service.getAll());
    }

    @WithMockUser(
            username = "Admin",
            authorities = "ROLE_ADMIN"
    )
    @Test
    @Transactional()
    public void deleteWithAdminAuth() {
        service.delete(bookExpected.getId());
        thrown.expect(NotFoundException.class);
        service.getById(bookExpected.getId());
    }


    @WithMockUser(
            username = "user",
            authorities = "ROLE_USER"
    )
    @Test(expected = AccessDeniedException.class)
    @Transactional()
    public void deleteWithUserAuth() {
        service.delete(bookExpected.getId());
        thrown.expect(NotFoundException.class);
        service.getById(bookExpected.getId());
    }

    @WithAnonymousUser
    @Test(expected = AccessDeniedException.class)
    @Transactional()
    public void deleteWithAnonymous() {
        service.delete(bookExpected.getId());
        thrown.expect(NotFoundException.class);
        service.getById(bookExpected.getId());
    }
}