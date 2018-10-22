package ru.otus.gromov;

import lombok.SneakyThrows;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.gromov.domain.Person;
import ru.otus.gromov.domain.Soul;
import ru.otus.gromov.service.MorgueService;

import java.util.Random;


@SpringBootApplication
public class Application {
    private static  Long POPULATION_OF_EARTH = 7_651_090_644L;

    @SneakyThrows
    public static void main(String[] args)  {
        ApplicationContext context = SpringApplication.run(Application.class);

        MorgueService morgueService = context.getBean(MorgueService.class);
        DataFactory df = new DataFactory();
        Random random = new Random();

        while (POPULATION_OF_EARTH > 0){
            morgueService.welcome(new Person(new Soul(df.getName(),random.nextInt(3))));
            Thread.sleep(1000);
            POPULATION_OF_EARTH--;
        }
    }
}
