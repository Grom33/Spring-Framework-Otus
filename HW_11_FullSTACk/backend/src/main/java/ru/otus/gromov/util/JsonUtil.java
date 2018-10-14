package ru.otus.gromov.util;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class JsonUtil {
    public static <T> String writeValue(T obj) {
        try {
            return JacksonObjectMapper.getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return JacksonObjectMapper.getMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }
}
