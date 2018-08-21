package ru.otus.gromov.to;

public class BookTo {

    String name;
    String description;
    Long[] genres;
    Long[] authors;

    public BookTo(String name, String description, Long[] genres, Long[] authors) {
        this.name = name;
        this.description = description;
        this.genres = genres;
        this.authors = authors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long[] getGenres() {
        return genres;
    }

    public void setGenres(Long[] genres) {
        this.genres = genres;
    }

    public Long[] getAuthors() {
        return authors;
    }

    public void setAuthors(Long[] authors) {
        this.authors = authors;
    }
}
