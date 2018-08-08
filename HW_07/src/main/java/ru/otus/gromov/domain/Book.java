package ru.otus.gromov.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Book")
public class Book {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_genre",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    @Column(name = "genres")
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.EAGER, cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    @Column(name = "authors")
    private Set<Author> authors;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            mappedBy = "book", orphanRemoval = true)
    private List<Comment> comments;

    public Book() {
    }

    public Book(Long id, String name, Set<Genre> genres, Set<Author> authors, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.authors = authors;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public Set<Genre> getGenres() {
        return this.genres;
    }

    public Set<Author> getAuthors() {
        return this.authors;
    }

    public List<Comment> getComments() {
        return comments;
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
        return !(this.id > 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) &&
                Objects.equals(genres, book.genres) &&
                Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genres, authors);
    }

    @Override
    public String toString() {
        return "Book{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", genres=" + genres +
                ", authors=" + authors +
                '}';
    }
}
