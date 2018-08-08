package ru.otus.gromov.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.gromov.TestData;


public class AuthorDaoJDBCTest extends AbstractDaoTest {

    @Autowired
    protected AuthorDao repository;

    @Test
    public void count() {
        Assert.assertEquals(3, repository.count());

    }

    @Test
    public void insert() {
        Assert.assertNull(repository.getByName(TestData.TEST_INSERTED_AUTHOR_WITH_ID_4.getName()));
        repository.insert(TestData.TEST_INSERTED_AUTHOR_WITH_ID_4);
        Assert.assertEquals(
                TestData.TEST_INSERTED_AUTHOR_WITH_ID_4,
                repository.getByName(TestData.TEST_INSERTED_AUTHOR_WITH_ID_4.getName()));
    }

    @Test
    public void getById() {
        Assert.assertEquals(TestData.TEST_AUTHOR_WITH_ID_1, repository.getById(1));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(TestData.LIST_WITH_ALL_AUTHORS, repository.getAll());
    }

    @Test
    public void getByName() {
        Assert.assertEquals(
                TestData.TEST_AUTHOR_WITH_ID_2,
                repository.getByName(TestData.TEST_AUTHOR_WITH_ID_2.getName()));
    }

    @Test
    public void delete() {
        Assert.assertNotNull(repository.getById(TestData.TEST_AUTHOR_WITH_ID_1.getId()));
        repository.delete(TestData.TEST_AUTHOR_WITH_ID_1.getId());
        Assert.assertNull(repository.getById(TestData.TEST_AUTHOR_WITH_ID_1.getId()));
    }

    @Test
    public void getByArrayID() {
        Assert.assertEquals(
                TestData.LIST_AUTHORS_BY_ARRAY_OF_ID,
                repository.getByArrayID(TestData.ARRAY_ID_OF_AUTHOR));

    }
}