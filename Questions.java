package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Questions {
    private List<Question> questions;

    public Questions() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public void deleteQuestion(String id) {
        questions.removeIf(q -> q.getId().equals(id));
    }

    public Optional<Question> getQuestionById(String id) {
        return questions.stream().filter(q -> q.getId().equals(id)).findFirst();
    }

    public List<Question> getAllQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "Questions: " + questions;
    }
}
