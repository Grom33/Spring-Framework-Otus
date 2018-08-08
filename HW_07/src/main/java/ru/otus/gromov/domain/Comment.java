package ru.otus.gromov.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "review", nullable = false, length = 150)
    private String review;

    @ManyToOne
    private Book book;

    public Comment(@NotBlank @Size(max = 150) String review, Book book) {
        this.review = review;
        this.book = book;
    }

    public Comment() {

    }

    public Long getId() {
        return id;
    }

    public String getReview() {
        return review;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", review='" + review + '\'' +
                '}';
    }
}
