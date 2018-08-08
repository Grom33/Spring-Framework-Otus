package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.gromov.config.ApplicationSettings;
import ru.otus.gromov.dao.TestLibraryRepository;
import ru.otus.gromov.domain.PassedTest;
import ru.otus.gromov.domain.TestQuestion;

import java.util.*;

@Service
public class TestingServiceImpl implements TestingService {

    final TestLibraryRepository testLibraryRepository;
    final ApplicationSettings settings;


    @Autowired
    public TestingServiceImpl(TestLibraryRepository testLibraryRepository, ApplicationSettings settings) {
        this.testLibraryRepository = testLibraryRepository;
        this.settings = settings;
    }

    @Override
    public TestQuestion getTestQuestionRandom() {
        List<TestQuestion> listQuestion = testLibraryRepository.getTestQuestionList();
        return listQuestion.get((int) (listQuestion.size() * Math.random()));
    }

    @Override
    public void setAnswerToPassedTest(PassedTest passedTest, TestQuestion testQuestion, int answer) {
        passedTest.setAnswer(testQuestion, answer);
    }

    @Override
    public Set<TestQuestion> getBatchTestQuestions(int count) {
        Set<TestQuestion> result = new HashSet<>();
        List<TestQuestion> listQuestion = testLibraryRepository.getTestQuestionList();

        while (result.size() < count) {
            result.add(listQuestion.get((int) (listQuestion.size() * Math.random())));
        }
        return result;
    }

    @Override
    public int getTestResult(PassedTest passedTest) {
        int rightAnswer = 0;
        for (Map.Entry<TestQuestion, Integer> entry : passedTest.getAnswers().entrySet()) {
            if (entry.getKey().isAnswerRight(entry.getValue())) rightAnswer++;
        }
        return rightAnswer;
    }
}
