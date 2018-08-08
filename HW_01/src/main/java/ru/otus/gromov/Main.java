package ru.otus.gromov;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.gromov.service.ConsoleTestingServiceImpl;

import java.io.IOException;

@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan

public class Main {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ConsoleTestingServiceImpl testingService = context.getBean(ConsoleTestingServiceImpl.class);
        testingService.startTesting();
    }
}
