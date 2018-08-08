package ru.otus.gromov.domain;

import java.util.HashMap;
import java.util.Map;

public class PassedTest {
    private Person person;
    private Map<TestQuestion, Integer> answers = new HashMap<>();

    public PassedTest() {
    }

    public PassedTest(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Map<TestQuestion, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<TestQuestion, Integer> answers) {
        this.answers = answers;
    }

    public void setAnswer(TestQuestion testQuestion, int answer){
        answers.put(testQuestion,answer);
    }

    @Override
    public String toString() {
        return "PassedTest{" +
                "person=" + person +
                ", answers=" + answers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassedTest that = (PassedTest) o;

        if (!person.equals(that.person)) return false;
        return answers != null ? answers.equals(that.answers) : that.answers == null;
    }

    @Override
    public int hashCode() {
        int result = person.hashCode();
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }
}
