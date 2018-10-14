package ru.otus.gromov.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
@Data
public class Comment {
    @Id
    private String id;

    private String review;
    private String bookId;

    public Comment(String review, String bookId) {
        this.review = review;
        this.bookId = bookId;
    }

    public Comment(String review) {
        this.review = review;
    }

}
