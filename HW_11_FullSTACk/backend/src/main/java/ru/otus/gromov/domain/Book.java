package ru.otus.gromov.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.gromov.event.CascadeSaveSetField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(isGetterVisibility = NONE)
@Document()
@Data
public class Book {

    @Id
    private String id;
    private String name;

    @DBRef
    @CascadeSaveSetField
    private Set<Genre> genres;

    @DBRef
    @CascadeSaveSetField
    private Set<Author> authors;

    private String description;

    private List<Comment> comments;

    public Book() {
    }

    public Book(String name, Set<Genre> genres, Set<Author> authors) {
        this(null, name, null, genres, authors, new ArrayList<>());
    }

    public Book(String name, String description, Set<Genre> genres, Set<Author> authors) {
        this(null, name, description, genres, authors, new ArrayList<>());
    }

    public Book(String name, Set<Genre> genres, Set<Author> authors, List<Comment> comments) {
        this(null, name, null, genres, authors, comments);
    }

    public Book(String id, String name, String description, Set<Genre> genres, Set<Author> authors, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.authors = authors;
        this.comments = comments;
        this.description = description;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) &&
                Objects.deepEquals(genres.toArray(), book.genres.toArray()) &&
                Objects.deepEquals(authors.toArray(), book.authors.toArray());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genres, authors);
    }

}
