package ru.otus.gromov.dao;

import ru.otus.gromov.domain.TestQuestion;

import java.util.List;

public interface TestLibraryRepository {

    List<TestQuestion> getTestQuestionList(String lang);

}
