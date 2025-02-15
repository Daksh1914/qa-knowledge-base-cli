package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void addAnswer(Answer a) {
        answers.add(a);
    }

    public void deleteAnswer(String id) {
        answers.removeIf(a -> a.getId().equals(id));
    }

    public void deleteAnswersByQuestionId(String questionId) {
        answers.removeIf(a -> a.getQuestionId().equals(questionId));
    }

    public Optional<Answer> getAnswerById(String id) {
        return answers.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    public List<Answer> getAnswersByQuestionId(String questionId) {
        return answers.stream()
                .filter(a -> a.getQuestionId().equals(questionId))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Answers: " + answers;
    }
}
