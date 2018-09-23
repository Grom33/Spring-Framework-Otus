package ru.otus.gromov.web;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


public class RootController {

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction() {
        HandlerFunction<ServerResponse> hello = request ->
                ServerResponse.ok().body(fromObject("Hello"));
        return route(GET("/hello"), hello);
    }
}
