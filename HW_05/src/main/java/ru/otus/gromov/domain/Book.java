package ru.otus.gromov.domain;

import java.util.HashSet;
import java.util.Set;

public class Book implements HasId {
    private int id;
    private String name;
    private Set<Integer> genres;
    private Set<Integer> authors;

    public Book(String name) {
        this(0, name);
    }

    public Book(int id, String name) {
        this(id, name, new HashSet<>(), new HashSet<>());
    }

    public Book(int id, String name, Set<Integer> genres, Set<Integer> authors) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.authors = authors;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Integer> getGenres() {
        return this.genres;
    }

    public void setGenres(Set<Integer> genres) {
        this.genres = genres;
    }

    public Set<Integer> getAuthors() {
        return this.authors;
    }

    public void setAuthors(Set<Integer> authors) {
        this.authors = authors;
    }

    public void addAuthor(Integer id) {
        this.authors.add(id);
    }

    public void addGenre(Integer id) {
        this.genres.add(id);
    }

    public boolean isNew() {
        return !(this.id > 0);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genres=" + genres +
                ", authors=" + authors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (!name.equals(book.name)) return false;
        if (genres != null ? !genres.equals(book.genres) : book.genres != null) return false;
        return authors != null ? authors.equals(book.authors) : book.authors == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }
}
