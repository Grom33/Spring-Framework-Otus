package ru.otus.gromov;

import ru.otus.gromov.domain.PassedTest;
import ru.otus.gromov.domain.Person;
import ru.otus.gromov.domain.TestQuestion;

import java.util.LinkedList;
import java.util.List;

public class TestData {

    public static final Person PERSON = new Person("Ivan", "Ivanov");
    public static final int TES_COUNT = 5;

    public static final TestQuestion TEST_QUESTION_1 = new TestQuestion();
    public static final TestQuestion TEST_QUESTION_2 = new TestQuestion();
    public static final TestQuestion TEST_QUESTION_3 = new TestQuestion();
    public static final TestQuestion TEST_QUESTION_4 = new TestQuestion();
    public static final TestQuestion TEST_QUESTION_5 = new TestQuestion();
    public static final TestQuestion TEST_QUESTION_6 = new TestQuestion();

    public static final PassedTest PASSED_TEST_ALL_ANSWERS_RIGHT = new PassedTest(PERSON);
    public static final PassedTest PASSED_TEST_CONTAIN_3_RIGHT_ANSWER = new PassedTest(PERSON);
    public static final PassedTest PASSED_TEST_WITH_1_ANSWER = new PassedTest(PERSON);

    public static final List<TestQuestion> LIST_OF_QUESTION_FROM_TEST_LIBRARY = new LinkedList<>();

    static {
        TEST_QUESTION_1.setQuestion("Как называют 30 летний юбилей свадьбы?");
        TEST_QUESTION_1.setAnswers(new String[]{"Зеленая","Золотая","Бронзовая","Жемчужная"});
        TEST_QUESTION_1.setRight(4);
        LIST_OF_QUESTION_FROM_TEST_LIBRARY.add(TEST_QUESTION_1);
        PASSED_TEST_WITH_1_ANSWER.setAnswer(TEST_QUESTION_1,1);

        TEST_QUESTION_2.setQuestion("Какое из этих произведений традиционно исполняли ночью?");
        TEST_QUESTION_2.setAnswers(new String[]{"Мадригал","Ноктюрн","Серенада","Романс"});
        TEST_QUESTION_2.setRight(3);
        LIST_OF_QUESTION_FROM_TEST_LIBRARY.add(TEST_QUESTION_2);

        TEST_QUESTION_3.setQuestion("Какая птица, согласно легендам, возрождается после самосожжения?");
        TEST_QUESTION_3.setAnswers(new String[]{"Феникс","Жар-Птица","Жаренный петух","Синяя птица"});
        TEST_QUESTION_3.setRight(1);
        LIST_OF_QUESTION_FROM_TEST_LIBRARY.add(TEST_QUESTION_3);

        TEST_QUESTION_4.setQuestion("Что обычно едят американцы на день благодарения?");
        TEST_QUESTION_4.setAnswers(new String[]{"Утку","Курицу","Индейку","Гуся"});
        TEST_QUESTION_4.setRight(3);
        LIST_OF_QUESTION_FROM_TEST_LIBRARY.add(TEST_QUESTION_4);

        TEST_QUESTION_5.setQuestion("Бутылку чего разбивают при спуске корабля на воду?");
        TEST_QUESTION_5.setAnswers(new String[]{"Рома","Водки","Шампанского","Пива"});
        TEST_QUESTION_5.setRight(3);
        LIST_OF_QUESTION_FROM_TEST_LIBRARY.add(TEST_QUESTION_5);

        TEST_QUESTION_6.setQuestion("Что согласно народным поверьям необходимо сделать, если черная кошка перебежит Вам дорогу?");
        TEST_QUESTION_6.setAnswers(new String[]{"Постучать по дереву","Кинуть в нее ботинком","Плюнуть через левое плечо","Перекреститься"});
        TEST_QUESTION_6.setRight(3);
        LIST_OF_QUESTION_FROM_TEST_LIBRARY.add(TEST_QUESTION_6);

        PASSED_TEST_ALL_ANSWERS_RIGHT.setAnswer(TEST_QUESTION_1, 4);
        PASSED_TEST_ALL_ANSWERS_RIGHT.setAnswer(TEST_QUESTION_2, 3);
        PASSED_TEST_ALL_ANSWERS_RIGHT.setAnswer(TEST_QUESTION_3, 1);
        PASSED_TEST_ALL_ANSWERS_RIGHT.setAnswer(TEST_QUESTION_4, 3);
        PASSED_TEST_ALL_ANSWERS_RIGHT.setAnswer(TEST_QUESTION_5, 3);

        PASSED_TEST_CONTAIN_3_RIGHT_ANSWER.setAnswer(TEST_QUESTION_1, 4);
        PASSED_TEST_CONTAIN_3_RIGHT_ANSWER.setAnswer(TEST_QUESTION_2, 3);
        PASSED_TEST_CONTAIN_3_RIGHT_ANSWER.setAnswer(TEST_QUESTION_3, 2);
        PASSED_TEST_CONTAIN_3_RIGHT_ANSWER.setAnswer(TEST_QUESTION_4, 2);
        PASSED_TEST_CONTAIN_3_RIGHT_ANSWER.setAnswer(TEST_QUESTION_5, 3);


    }


}
