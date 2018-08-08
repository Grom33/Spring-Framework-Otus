package ru.otus.gromov.domain;

import java.util.Arrays;
import java.util.Objects;

public class TestQuestion {
    private String question;
    private String[] answers;
    private byte right;

    public TestQuestion() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getRight() {
        return answers[right];
    }

    public void setRight(int right) {
        this.right = (byte) right;
    }

    public boolean isAnswerRight(int answer) {
        return right == answer;
    }

    @Override
    public String toString() {
        return "TestQuestion{" +
                "question='" + question + '\'' +
                ", answers=" + Arrays.toString(answers) +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestQuestion that = (TestQuestion) o;
        return Objects.equals(question, that.question) &&
                Arrays.equals(answers, that.answers) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(question, right);
        result = 31 * result + Arrays.hashCode(answers);
        return result;
    }
}
