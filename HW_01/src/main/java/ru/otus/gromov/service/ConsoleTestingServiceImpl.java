package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.gromov.domain.PassedTest;
import ru.otus.gromov.domain.Person;
import ru.otus.gromov.domain.TestQuestion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Set;

@Service
public class ConsoleTestingServiceImpl {
    private final MessageSource messageSource;
    private final TestingService testingService;
    private Locale lang;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private int testCount;

    @Autowired
    public ConsoleTestingServiceImpl(TestingService testingService, MessageSource messageSource, @Value("${test.local}") String lang, @Value("${test.countQuestions}") int count) {
        this.testingService = testingService;
        this.messageSource = messageSource;
        this.lang = Locale.forLanguageTag(lang);
        this.testCount = count;
    }

    public void startTesting() throws IOException {
        Person person = new Person();
        PassedTest passedTest = new PassedTest(person);
        selectLanguage();
        System.out.println("========================================");
        System.out.println("            " + messageSource.getMessage("console.header", null, lang));
        System.out.println("========================================");
        System.out.print(messageSource.getMessage("console.enterName", null, lang));
        person.setName(reader.readLine());
        System.out.print(messageSource.getMessage("console.enterSurname", null, lang));
        person.setSurname(reader.readLine());
        System.out.println("========================================");
        doTestingLoops(testingService.getBatchTestQuestions(testCount, lang.toString()), passedTest);
        printTestingResult(passedTest);

    }

    private void doTestingLoops(Set<TestQuestion> questions, PassedTest passedTest) {
        for (TestQuestion aSetQuestion : questions) {
            printQuestion(aSetQuestion);
            System.out.print(messageSource.getMessage("console.InputInstructionMain", null, lang));

            testingService.setAnswerToPassedTest(passedTest, aSetQuestion, readAnswerInteger(1, aSetQuestion.getAnswers().length));
            System.out.println("----------------------------------------");

        }
    }

    private void printTestingResult(PassedTest passedTest) {
        System.out.println("========================================");
        System.out.println("            " + messageSource.getMessage("console.resultHeader", null, lang));
        System.out.println("========================================");
        System.out.println("= " + messageSource.getMessage("console.personLabel", null, lang)
                + passedTest.getPerson().getName() + " " + passedTest.getPerson().getSurname());
        System.out.println("= " + messageSource.getMessage("console.score", null, lang)
                + testingService.getTestResult(passedTest));
        System.out.println("========================================");
    }


    public void printQuestion(TestQuestion question) {
        System.out.println(messageSource.getMessage("console.questionLabel", null, lang) + question.getQuestion());
        System.out.print(messageSource.getMessage("console.answerLabel", null, lang));
        for (int i = 0; i < question.getAnswers().length; i++) {
            System.out.print(" " + (i + 1) + ") " + question.getAnswers()[i]);
        }
        System.out.println();
    }

    private void selectLanguage() {
        System.out.println("Пожалуйста, нажмите 1 для выбора руского языка.");
        System.out.println("Please, press 2 to english");
        int answerValue = readAnswerInteger(1, 2);
        if (answerValue == 1) {
            lang = Locale.forLanguageTag("ru");
        } else {
            lang = Locale.forLanguageTag("en");
        }
        System.out.println(lang.toString());
    }

    private Integer readAnswerInteger(int minValue, int maxValue) {
        Integer ans = 0;
        while (ans == 0) {
            try {
                int answerValue = Integer.parseInt(reader.readLine());
                if (answerValue < minValue || answerValue > maxValue) {
                    System.out.println(messageSource.getMessage("console.inputRange", null, lang) + maxValue);
                    System.out.print(messageSource.getMessage("console.InputInstructionMain", null, lang));
                } else {
                    ans = answerValue;
                }
            } catch (Exception e) {
                System.out.println(messageSource.getMessage("console.inputRange", null, lang) + maxValue);
                System.out.print(messageSource.getMessage("console.InputInstructionMain", null, lang));
            }
        }
        return ans;
    }
}
