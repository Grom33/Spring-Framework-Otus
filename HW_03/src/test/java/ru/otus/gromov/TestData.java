package ru.otus.gromov;

import ru.otus.gromov.domain.Author;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.to.BoookWithPrintableFields;

import java.util.*;

public class TestData {
    public static final Author TEST_AUTHOR_WITH_ID_1 = new Author(1, "Joshua Bloch");
    public static final Author TEST_AUTHOR_WITH_ID_2 = new Author(2, "Brian Goetz");
    public static final Author TEST_AUTHOR_WITH_ID_3 = new Author(3, "Doug Lea");

    public static final Author TEST_INSERTED_AUTHOR_WITH_ID_4 = new Author(4, "TEST AUTHOR");

    public static final List<Author> LIST_WITH_ALL_AUTHORS = new ArrayList<>(
            Arrays.asList(
                    TEST_AUTHOR_WITH_ID_1,
                    TEST_AUTHOR_WITH_ID_2,
                    TEST_AUTHOR_WITH_ID_3));

    public static final Integer[] ARRAY_ID_OF_AUTHOR = new Integer[]{1, 3};

    public static final List<Author> LIST_AUTHORS_BY_ARRAY_OF_ID = new ArrayList<>(
            Arrays.asList(
                    TEST_AUTHOR_WITH_ID_1,
                    TEST_AUTHOR_WITH_ID_3));

    public static final Genre TEST_GENRE_WITH_ID_1 = new Genre(1, "Patterns");
    public static final Genre TEST_GENRE_WITH_ID_2 = new Genre(2, "Java");
    public static final Genre TEST_GENRE_WITH_ID_3 = new Genre(3, "Concurency");

    public static final Genre TEST_INSERTED_GENRE_WITH_ID_4 = new Genre(4, "TEST GENRE");

    public static final List<Genre> LIST_WITH_ALL_GENRES = new ArrayList<>(
            Arrays.asList(
                    TEST_GENRE_WITH_ID_1,
                    TEST_GENRE_WITH_ID_2,
                    TEST_GENRE_WITH_ID_3));

    public static final Integer[] ARRAY_ID_OF_GENRE = new Integer[]{1, 2};

    public static final List<Genre> LIST_GENRES_BY_ARRAY_OF_ID = new ArrayList<>(
            Arrays.asList(
                    TEST_GENRE_WITH_ID_1,
                    TEST_GENRE_WITH_ID_2));

    public static final Book TEST_BOOK_WITH_ID_1 = new Book(
            1,
            "Effective java",
            new HashSet<>(Arrays.asList(1, 2)),
            new HashSet<>(Collections.singletonList(1)));

    public static final Book TEST_BOOK_WITH_ID_2 = new Book(
            2,
            "Java concurrency in practice",
            new HashSet<>(Arrays.asList(2, 3)),
            new HashSet<>(Arrays.asList(1, 2, 3)));

    public static final Book TEST_BOOK_WITH_ID_3 = new Book(
            3,
            "Concurrent Programming in Java",
            new HashSet<>(Arrays.asList(1, 2)),
            new HashSet<>(Collections.singletonList(3)));

    public static final Book TEST_INSERTED_BOOK_WITH_ID_4 = new Book(
            4,
            "TEST BOOK",
            new HashSet<>(Arrays.asList(1, 2)),
            new HashSet<>(Arrays.asList(1, 2)));

    public static final Book TEST_UPDATETD_BOOK_WITH_ID_2 = new Book(
            2,
            "TEST UPDATED BOOK",
            new HashSet<>(Arrays.asList(1)),
            new HashSet<>(Arrays.asList(1, 3)));

    public static final List<Book> LIST_WITH_ALL_BOOKS = new ArrayList<>(
            Arrays.asList(
                    TEST_BOOK_WITH_ID_1,
                    TEST_BOOK_WITH_ID_2,
                    TEST_BOOK_WITH_ID_3));

    public static final BoookWithPrintableFields TEST_PRINTABLE_BOOK_WITH_ID_1 = new BoookWithPrintableFields(
            1,
            "Effective java",
            new ArrayList<Genre>(Arrays.asList(TEST_GENRE_WITH_ID_1, TEST_GENRE_WITH_ID_2)),
            new ArrayList<Author>(Arrays.asList(TEST_AUTHOR_WITH_ID_1)));
}
