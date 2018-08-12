package ru.otus.gromov.domain;


import java.util.Objects;

public class Comment {
    private Long id;
    private String review;

    public Comment(String review) {
        this.review = review;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public String getReview() {
        return review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(review, comment.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, review);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", review='" + review + '\'' +
                '}';
    }
}
