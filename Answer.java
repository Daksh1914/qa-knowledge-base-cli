package models;

public class Answer {
    private String id;
    private String text;
    private String questionId;
    private static int counter = 1;  // Used to generate IDs like A1, A2, ...

    // Constructor for new answers (auto-generates a short ID)
    public Answer(String text, String questionId) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer text cannot be empty.");
        }
        if (questionId == null || questionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Question ID cannot be empty for an answer.");
        }
        this.id = "A" + counter;
        counter++;
        this.text = text;
        this.questionId = questionId;
    }

    // Constructor for loading an answer from file (ID is provided)
    public Answer(String id, String text, String questionId) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer id cannot be empty.");
        }
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer text cannot be empty.");
        }
        if (questionId == null || questionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Question ID cannot be empty for an answer.");
        }
        this.id = id;
        this.text = text;
        this.questionId = questionId;
        // Update counter if necessary so that future IDs are unique
        try {
            int num = Integer.parseInt(id.substring(1));
            if (num >= counter) {
                counter = num + 1;
            }
        } catch (NumberFormatException e) {
            // If the ID is not in the expected format, ignore updating the counter.
        }
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setText(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer text cannot be empty.");
        }
        this.text = text;
    }

    @Override
    public String toString() {
        return "[ID: " + id + "] A: " + text;
    }
}
