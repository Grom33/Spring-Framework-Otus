package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.gromov.dao.TestLibraryRepository;
import ru.otus.gromov.domain.PassedTest;
import ru.otus.gromov.domain.TestQuestion;

import java.util.*;

@Service
public class TestingServiceImpl implements TestingService {

    final TestLibraryRepository testLibraryRepository;


    @Autowired
    public TestingServiceImpl(TestLibraryRepository testLibraryRepository) {
        this.testLibraryRepository = testLibraryRepository;

    }

    @Override
    public TestQuestion getTestQuestionRandom(String lang) {
        List<TestQuestion> listQuestion = testLibraryRepository.getTestQuestionList(lang);
        return listQuestion.get((int) (listQuestion.size() * Math.random()));
    }

    @Override
    public void setAnswerToPassedTest(PassedTest passedTest, TestQuestion testQuestion, int answer) {
        passedTest.setAnswer(testQuestion, answer - 1);
    }

    @Override
    public Set<TestQuestion> getBatchTestQuestions(int count, String lang) {
        Set<TestQuestion> result = new HashSet<>();
        List<TestQuestion> listQuestion = testLibraryRepository.getTestQuestionList(lang);

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
