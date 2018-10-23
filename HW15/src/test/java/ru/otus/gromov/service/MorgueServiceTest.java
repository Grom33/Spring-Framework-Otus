package ru.otus.gromov.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.gromov.domain.Person;
import ru.otus.gromov.domain.Soul;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
public class MorgueServiceTest {

    @Autowired
    MorgueService morgueService;

    @Autowired
    HeavenService heavenService;

    @Autowired
    HellService hellService;

    @Autowired
    GodJudgmentService godJudgmentService;


    @Test
    public void welcomeTest() throws InterruptedException {
        Person John = new Person(new Soul("John", 12));
        Person Jane = new Person(new Soul("Jane", 0));

        morgueService.welcome(John);
        morgueService.welcome(Jane);

        Thread.sleep(1000);

        Assert.assertEquals("1 Soul has come to hell through the hellChannel", 1, hellService.getHellPopulation());
        Assert.assertEquals("1 Soul has come to paradise through the paradiseChannel", 1, heavenService.getParadisePopulation());
    }

    @Test
    public void overSoulParadiseTest() throws InterruptedException {

        for (int i = 0; i < 200; i++) {
            Person john = new Person(new Soul("John_" + i, 0));
            morgueService.welcome(john);
        }

        Thread.sleep(2000);

        Assert.assertEquals("Heaven is full", true, godJudgmentService.isHeavenIsFull());

    }
}