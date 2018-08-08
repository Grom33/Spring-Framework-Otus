package ru.otus.gromov.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.gromov.config.ApplicationSettings;
import ru.otus.gromov.domain.PassedTest;
import ru.otus.gromov.domain.Person;
import ru.otus.gromov.domain.TestQuestion;
import ru.otus.gromov.service.TestingService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Set;

@ShellComponent
public class TestCommands {
    private final TestingService service;
    private final MessageSource messageSource;
    private final ApplicationSettings settings;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final PrintStream out = System.out;
    private PassedTest passedTest;
    private Person person;

    @Autowired
    public TestCommands(TestingService service, MessageSource messageSource, ApplicationSettings settings) {
        this.service = service;
        this.messageSource = messageSource;
        this.settings = settings;
    }

    @ShellMethod(value = "Insert new user. Enter your full name")
    public void newUser(@ShellOption String name, @ShellOption String surname) {
        this.person = new Person(name, surname);
    }

    @ShellMethod(value = "Print to start test")
    public void start() {
        this.passedTest = new PassedTest(person);
        out.println("========================================");
        out.println("            " + getMessageFromBundle("console.header"));
        out.println("========================================");

        Set<TestQuestion> questions = service.getBatchTestQuestions(settings.getCountQuestions());
        for (TestQuestion aSetQuestion : questions) {
            printQuestion(aSetQuestion);
            out.print(getMessageFromBundle("console.InputInstructionMain"));
            service.setAnswerToPassedTest(passedTest, aSetQuestion, readAnswerInteger(1, aSetQuestion.getAnswers().length));
            out.println("----------------------------------------");
        }
        printTestingResult(passedTest);
    }

    Availability startAvailability() {
        return !(this.person == null) ?
                Availability.available() :
                Availability.unavailable("There is no user in the system! Use new-user command");
    }

    @ShellMethod(value = "Set language. Type 'ru' or 'en'")
    public void language(@ShellOption String language) {
        settings.setLocal(language);
    }

    @ShellMethod(value = "Type to print result")
    public void result() {
        printTestingResult(passedTest);
    }

    Availability resultAvailability() {
        return !(this.passedTest == null) ?
                Availability.available() :
                Availability.unavailable("There is no passed test in the system! Use start command");
    }

    private void printTestingResult(PassedTest passedTest) {
        out.print("\u001B[34m");
        out.println("========================================");
        out.println("            " + getMessageFromBundle("console.resultHeader"));
        out.println("========================================");
        out.println("= " + getMessageFromBundle("console.personLabel")
                + passedTest.getPerson().getName() + " " + passedTest.getPerson().getSurname());
        out.println("= " + getMessageFromBundle("console.score")
                + service.getTestResult(passedTest));
        out.println("========================================");
        out.print("\u001B[0m");
    }

    private void printQuestion(TestQuestion question) {
        out.print("\u001B[33m");
        out.println(getMessageFromBundle("console.questionLabel") + question.getQuestion());
        out.print("\u001B[32m");
        out.print(getMessageFromBundle("console.answerLabel"));
        out.print("\u001B[31m");
        for (int i = 0; i < question.getAnswers().length; i++) {
            out.print(" " + (i + 1) + ") " + question.getAnswers()[i]);
        }
        out.println();
        out.print("\u001B[0m");
    }

    private Integer readAnswerInteger(int minValue, int maxValue) {
        Integer ans = 0;
        while (ans == 0) {
            try {
                int answerValue = Integer.parseInt(reader.readLine());
                if (answerValue < minValue || answerValue > maxValue) {
                    printInputErrorMessage(maxValue);
                } else {
                    ans = answerValue;
                }
            } catch (Exception e) {
                printInputErrorMessage(maxValue);
            }
        }
        return ans;
    }

    private void printInputErrorMessage(int maxValue) {
        out.print("\u001B[31m");
        out.println(getMessageFromBundle("console.inputRange") + maxValue);
        out.print(getMessageFromBundle("console.InputInstructionMain"));
        out.print("\u001B[0m");
    }

    private String getMessageFromBundle(String msg) {
        return messageSource.getMessage(msg, null, Locale.forLanguageTag(settings.getLocal()));
    }

}
