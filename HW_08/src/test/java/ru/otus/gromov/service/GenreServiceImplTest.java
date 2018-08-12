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
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class GenreServiceImplTest {
    private final Genre TEST_GENRE_1 = new Genre("Test1");
    private final Genre TEST_GENRE_2 = new Genre("Test2");
    private final Genre TEST_GENRE_3 = new Genre("Test3");

    @Autowired
    GenreService service;

    @Before
    public void setUp() {
        service.save(TEST_GENRE_1);
        service.save(TEST_GENRE_2);
        service.save(TEST_GENRE_3);
    }

    @Test
    public void count() {
        Assert.assertEquals("Get count of Genre", 3, service.count());
    }

    @Test
    public void save() {
        Genre testGenre = service.save(new Genre("test4"));
        Assert.assertEquals("Save Genre",
                testGenre,
                service.getById(testGenre.getId()));
        Assert.assertEquals("Save Genre, check count",
                4,
                service.count());
    }

    @Test
    public void getById() {
        Assert.assertEquals("Save Genre",
                TEST_GENRE_1,
                service.getById(TEST_GENRE_1.getId()));
    }

    @Test
    public void getByName() {
        Assert.assertEquals("Save Genre",
                TEST_GENRE_1,
                service.getByName(TEST_GENRE_1.getName()));
    }

    @Test
    public void getAll() {
        List<Genre> expecteList = Arrays.asList(TEST_GENRE_1, TEST_GENRE_2, TEST_GENRE_3);
        Assert.assertEquals("Get all Genre",
                expecteList,
                service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        List<Genre> expecteList = Arrays.asList(TEST_GENRE_2, TEST_GENRE_3);
        service.delete(TEST_GENRE_1.getId());
        Assert.assertEquals("Deleted Genre is absent in list",
                expecteList,
                service.getAll());
        service.getById(TEST_GENRE_1.getId());
    }
}