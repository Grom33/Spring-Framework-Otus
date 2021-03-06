package ru.otus.gromov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.gromov.config.ApplicationSettings;
import ru.otus.gromov.domain.TestQuestion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CsvTestLibraryRepositoryImpl implements TestLibraryRepository {

    private String csvFilePath;
    private final ApplicationSettings settings;

    @Autowired
    public CsvTestLibraryRepositoryImpl(ApplicationSettings settings) {
        this.csvFilePath = getClass().getResource(settings.getCsv()).getFile();
        this.settings = settings;
    }

    @Override
    public List<TestQuestion> getTestQuestionList() {
        List<TestQuestion> ListTestQuestions = new LinkedList<>();
        String cvsSplitBy = "##";
        boolean canAddToResultList = false;
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(csvFilePath), "UTF-8"))) {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                String[] questionLine = line.split(cvsSplitBy);
                final int[] indexOfanswer = {0};
                if (questionLine.length > 1 && canAddToResultList) {
                    TestQuestion testQuestion = new TestQuestion();
                    testQuestion.setQuestion(questionLine[0].trim());
                    testQuestion.setAnswers(Arrays.stream(Arrays.copyOfRange(questionLine, 1, questionLine.length))
                            .map((item) -> {
                                indexOfanswer[0]++;
                                if (item.contains("%R%")) testQuestion.setRight(indexOfanswer[0]);
                                return item.replace("%R%", "").trim();
                            })
                            .toArray(String[]::new));
                    ListTestQuestions.add(testQuestion);
                } else {
                    canAddToResultList = questionLine[0].equals(settings.getLocal());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListTestQuestions;
    }

    public void setPathToCsv(String path) {
        csvFilePath = path;
    }
}
