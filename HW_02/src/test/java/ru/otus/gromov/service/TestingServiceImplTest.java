package ru.otus.gromov.service;

import ru.otus.gromov.AbstractServiceTest;
import ru.otus.gromov.domain.PassedTest;
import ru.otus.gromov.domain.TestQuestion;
import org.junit.Assert;
import org.junit.Test;
import ru.otus.gromov.TestData;

import java.util.Set;

public class TestingServiceImplTest extends AbstractServiceTest {

    @Test
    public void setAnswerToPassedTest() {
        PassedTest passedTest1 = new PassedTest(TestData.PERSON);
        testingService.setAnswerToPassedTest(passedTest1,TestData.TEST_QUESTION_1,1);
        Assert.assertEquals(passedTest1, TestData.PASSED_TEST_WITH_1_ANSWER);
    }

    @Test
    public void getBatchTestQuestions() {
       Set<TestQuestion> setQuestion = testingService.getBatchTestQuestions(TestData.TES_COUNT);
       Assert.assertEquals(setQuestion.size(),TestData.TES_COUNT);
       Set<TestQuestion> secondQuestion = testingService.getBatchTestQuestions(TestData.TES_COUNT);
       Assert.assertNotEquals(setQuestion,secondQuestion);
    }

    @Test
    public void getTestResult(){
        Assert.assertEquals(testingService.getTestResult(TestData.PASSED_TEST_CONTAIN_3_RIGHT_ANSWER),3);
        Assert.assertEquals(testingService.getTestResult(TestData.PASSED_TEST_ALL_ANSWERS_RIGHT),5);
    }
}