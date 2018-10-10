package ru.otus.gromov;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.SQLException;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
@EnableSwagger2
public class Application {
    public static void main(String[] args) throws SQLException {// Console.main(args);
        SpringApplication.run(Application.class);
    }

}
