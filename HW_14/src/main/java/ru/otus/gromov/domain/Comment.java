package ru.otus.gromov.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "Comment")
@Data
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

}
