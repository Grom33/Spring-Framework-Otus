package ru.otus.gromov.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;


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

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    public Comment(@NotBlank @Size(max = 150) String review, Long bookId) {
        this.review = review;
            this.bookId = bookId;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public String getReview() {
        return review;
    }

    public Long getBookId() {
        return bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(review, comment.review) &&
                Objects.equals(bookId, comment.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, review, bookId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", review='" + review + '\'' +
                '}';
    }
}
