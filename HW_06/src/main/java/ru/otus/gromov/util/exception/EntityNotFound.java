package ru.otus.gromov.util.exception;

public class EntityNotFound extends RuntimeException {
    public EntityNotFound(String message) {
        super(message);
    }
}