package ru.otus.gromov;

import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.gromov.service.TestingService;
import ru.otus.gromov.service.TestingServiceImpl;

import java.util.Locale;

public abstract class  AbstractServiceTest {
    protected TestingService testingService;
    protected Locale lang = Locale.forLanguageTag("ru");


    @Before
    public void setUp() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        testingService          = context.getBean(TestingServiceImpl.class);
    }

}
