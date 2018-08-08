package ru.otus.gromov.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.gromov.TestData;
import ru.otus.gromov.util.exception.NotFoundException;


public class AuthorServiceImplTest extends AbstractServiceTest {
    @Autowired
    protected AuthorService service;

    @Test
    public void count() {
        Assert.assertEquals(3, service.count());
    }

    @Test(expected = NotFoundException.class)
    public void insert() {
        service.getByName(TestData.TEST_INSERTED_AUTHOR_WITH_ID_4.getName());
        service.insert(TestData.TEST_INSERTED_AUTHOR_WITH_ID_4);
        Assert.assertEquals(
                TestData.TEST_INSERTED_AUTHOR_WITH_ID_4,
                service.getByName(TestData.TEST_INSERTED_AUTHOR_WITH_ID_4.getName()));
    }

    @Test
    public void getById() {
        Assert.assertEquals(TestData.TEST_AUTHOR_WITH_ID_1, service.getById(1));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(TestData.LIST_WITH_ALL_AUTHORS, service.getAll());
    }

    @Test
    public void getByName() {
        Assert.assertEquals(
                TestData.TEST_AUTHOR_WITH_ID_2,
                service.getByName(TestData.TEST_AUTHOR_WITH_ID_2.getName()));
    }

    @Test
    public void getbyArrayID() {
        Assert.assertEquals(
                TestData.LIST_AUTHORS_BY_ARRAY_OF_ID,
                service.getbyArrayID(TestData.ARRAY_ID_OF_AUTHOR));
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        Assert.assertEquals(TestData.TEST_AUTHOR_WITH_ID_1, service.getById(TestData.TEST_AUTHOR_WITH_ID_1.getId()));
        service.delete(TestData.TEST_AUTHOR_WITH_ID_1.getId());
        service.getById(TestData.TEST_AUTHOR_WITH_ID_1.getId());
    }
}