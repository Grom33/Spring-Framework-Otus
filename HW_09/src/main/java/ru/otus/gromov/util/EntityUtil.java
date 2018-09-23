package ru.otus.gromov.util;

import ru.otus.gromov.domain.Author;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.service.AuthorService;
import ru.otus.gromov.service.GenreService;
import ru.otus.gromov.to.BookTo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EntityUtil {

    public static Book bookToToBook(BookTo bookTo, Book book, AuthorService authorService, GenreService genreService) {
        book.setName(bookTo.getName());
        book.setDescription(bookTo.getDescription());
        book.setGenres(arrayToSetGenre(bookTo.getGenres(), genreService));
        book.setAuthors(arrayToSetAuthor(bookTo.getAuthors(), authorService));
        return book;
    }

    private static Set<Author> arrayToSetAuthor(Long[] array, AuthorService authorService) {
        Set<Author> result = new HashSet<>();
        Arrays.stream(array).forEach(i -> result.add(authorService.getById(i)));
        return result;
    }

    private static Set<Genre> arrayToSetGenre(Long[] array, GenreService genreService) {
        Set<Genre> result = new HashSet<>();
        Arrays.stream(array).forEach(i -> result.add(genreService.getById(i)));
        return result;
    }

}
