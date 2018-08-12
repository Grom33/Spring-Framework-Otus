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
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class AuthorServiceImplTest {

    private final Author TEST_AUTHOR1 = new Author("Test1");
    private final Author TEST_AUTHOR2 = new Author("Test2");
    private final Author TEST_AUTHOR3 = new Author("Test3");

    @Autowired
    AuthorService service;

    @Before
    public void init() {
        service.save(TEST_AUTHOR1);
        service.save(TEST_AUTHOR2);
        service.save(TEST_AUTHOR3);
    }

    @Test
    public void count() {
        Assert.assertEquals("Count entity in base", 3, service.count());
    }

    @Test
    public void save() {
        Author testAuthor = service.save(new Author("Test4"));
        Assert.assertEquals("Isert Author to base",
                testAuthor,
                service.getById(testAuthor.getId()));
        Assert.assertEquals("Count entity in base", 4, service.count());
    }

    @Test
    public void getById() {
        Assert.assertEquals("Get Author by ID",
                TEST_AUTHOR1,
                service.getById(TEST_AUTHOR1.getId()));
    }

    @Test
    public void getByName() {
        Assert.assertEquals("Get Author by Name",
                TEST_AUTHOR1,
                service.getByName(TEST_AUTHOR1.getName()));
    }

    @Test
    public void getAll() {
        List<Author> expectedList = Arrays.asList(TEST_AUTHOR1, TEST_AUTHOR2, TEST_AUTHOR3);
        Assert.assertEquals("Get all Authors",
                expectedList,
                service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        List<Author> expectedList = Arrays.asList(TEST_AUTHOR2, TEST_AUTHOR3);
        service.delete(TEST_AUTHOR1.getId());
        Assert.assertEquals("Deleted Author is absent in list",
                expectedList,
                service.getAll());
        service.getById(TEST_AUTHOR1.getId());
    }
}