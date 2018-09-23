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
        service.save(TEST_AUTHOR1).block();
        service.save(TEST_AUTHOR2).block();
        service.save(TEST_AUTHOR3).block();
    }

    @Test
    public void count() {
        Assert.assertEquals("Count entity in base", 3L, service.count().block().longValue());
    }

    @Test
    public void save() {
        Author testAuthor = service.save(new Author("Test4")).block();
        Assert.assertEquals("Insert Author to base",
                testAuthor,
                service.getById(testAuthor.getId()).block());
        Assert.assertEquals("Count entity in base", 4L, service.count().block().longValue());
    }

    @Test
    public void getById() {
        Assert.assertEquals("Get Author by ID",
                TEST_AUTHOR1,
                service.getById(TEST_AUTHOR1.getId()).block());
    }

    @Test
    public void getByName() {
        Assert.assertEquals("Get Author by Name",
                TEST_AUTHOR1,
                service.getByName(TEST_AUTHOR1.getName()).block());
    }

    @Test
    public void getAll() {
        List<Author> expectedList = Arrays.asList(TEST_AUTHOR1, TEST_AUTHOR2, TEST_AUTHOR3);
        Assert.assertEquals("Get all Authors",
                expectedList,
                service.getAll().collectList().block());
    }

    @Test
    public void delete() {
        List<Author> expectedList = Arrays.asList(TEST_AUTHOR2, TEST_AUTHOR3);
        service.delete(TEST_AUTHOR1.getId()).block();
        Assert.assertEquals("Deleted Author is absent in list",
                expectedList,
                service.getAll().collectList().block());
    }
}