package ru.otus.gromov.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.*;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class BookServiceImplTest {
    @Autowired
    BookService service;

    private final Author TEST_AUTHOR1 = new Author("Test1");
    private final Author TEST_AUTHOR2 = new Author("Test2");

    private final Genre TEST_GENRE_1 = new Genre("Test1");
    private final Genre TEST_GENRE_2 = new Genre("Test2");

    private final Book TEST_BOOK_1 = new Book("TestBook1",
            new HashSet<>(Arrays.asList(TEST_GENRE_1, TEST_GENRE_2)),
            new HashSet<>(Collections.singletonList(TEST_AUTHOR1)));
    private final Book TEST_BOOK_2 = new Book("TestBook2",
            new HashSet<>(Collections.singletonList(TEST_GENRE_1)),
            new HashSet<>(Arrays.asList(TEST_AUTHOR1, TEST_AUTHOR2)));
    private final Book TEST_BOOK_3 = new Book("TestBook3",
            new HashSet<>(Arrays.asList(TEST_GENRE_1, TEST_GENRE_2)),
            new HashSet<>(Arrays.asList(TEST_AUTHOR1, TEST_AUTHOR2)));

    @Before
    public void setUp() {
        service.save(TEST_BOOK_1);
        service.save(TEST_BOOK_2);
        service.save(TEST_BOOK_3);
    }

    @Test
    public void count() {
        Assert.assertEquals("Count of Books", 3, service.count());
    }

    @Test
    public void save() {
        Book testBook = service.save(new Book("TestBook4",
                new LinkedHashSet<>(Arrays.asList(TEST_GENRE_1, TEST_GENRE_2)),
                new LinkedHashSet<>(Collections.singletonList(TEST_AUTHOR1))));
        Assert.assertEquals("Insert Book to base",
                testBook,
                service.getById(testBook.getId()));
        Assert.assertEquals("Count entity in base", 4, service.count());
    }

    @Test
    public void getById() {
        Assert.assertEquals("Get Book by ID",
                TEST_BOOK_1,
                service.getById(TEST_BOOK_1.getId()));
    }

    @Test
    public void getByName() {
        Assert.assertEquals("Get Author by Name",
                TEST_BOOK_1,
                service.getByName(TEST_BOOK_1.getName()));
    }

    @Test
    public void getAll() {
        List<Book> expectedList = Arrays.asList(TEST_BOOK_1, TEST_BOOK_2, TEST_BOOK_3);
        Assert.assertEquals("Get all Books",
                expectedList,
                service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        List<Book> expectedList = new ArrayList<>(Arrays.asList(TEST_BOOK_2, TEST_BOOK_3));
        service.delete(TEST_BOOK_1.getId());
        Assert.assertEquals("Deleted Author is absent in list",
                expectedList,
                service.getAll());
        service.getById(TEST_BOOK_1.getId());
    }
}