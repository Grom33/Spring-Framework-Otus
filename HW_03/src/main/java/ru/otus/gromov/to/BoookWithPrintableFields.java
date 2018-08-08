package ru.otus.gromov.to;

import ru.otus.gromov.domain.Author;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Genre;

import java.util.List;

public class BoookWithPrintableFields {
    private int id;
    private String name;
    private List<Genre> genres;
    private List<Author> authors;

    public BoookWithPrintableFields(int id, String name, List<Genre> genres, List<Author> authors) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.authors = authors;
    }

    public BoookWithPrintableFields() {
    }

    public BoookWithPrintableFields(Book book) {
        this.id = book.getId();
        this.name = book.getName();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoookWithPrintableFields that = (BoookWithPrintableFields) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (genres != null ? !genres.equals(that.genres) : that.genres != null) return false;
        return authors != null ? authors.equals(that.authors) : that.authors == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BoookWithPrintableFields{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genres=" + genres +
                ", authors=" + authors +
                '}';
    }
}
