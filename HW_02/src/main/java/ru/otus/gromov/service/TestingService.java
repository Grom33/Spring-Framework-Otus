package ru.otus.gromov.service;

import ru.otus.gromov.domain.PassedTest;
import ru.otus.gromov.domain.TestQuestion;

import java.util.Set;

public interface TestingService {

    TestQuestion getTestQuestionRandom();

     void setAnswerToPassedTest(PassedTest passedTest, TestQuestion testQuestion, int answer);

    Set<TestQuestion> getBatchTestQuestions(int count);

    int getTestResult(PassedTest passedTest);



}
