package models;

public class Question {
    private String id;
    private String text;
    private static int counter = 1;  // Used to generate IDs like Q1, Q2, ...

    // Constructor for new questions (auto-generates a short ID)
    public Question(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be empty.");
        }
        this.id = "Q" + counter;
        counter++;
        this.text = text;
    }

    // Constructor for loading a question from file (ID is provided)
    public Question(String id, String text) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Question id cannot be empty.");
        }
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be empty.");
        }
        this.id = id;
        this.text = text;
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

    public void setText(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be empty.");
        }
        this.text = text;
    }

    @Override
    public String toString() {
        return "[ID: " + id + "] Q: " + text;
    }
}
