package main;

import models.Question;
import models.Answer;
import java.io.*;
import java.util.*;

public class main {
    // File paths for persistence
    private static final String QUESTIONS_FILE = "questions.txt";
    private static final String ANSWERS_FILE = "answers.txt";

    public static void main(String[] args) {
        // Load questions and answers from files into HashMaps
        HashMap<String, Question> questionMap = loadQuestions();
        HashMap<String, Answer> answerMap = loadAnswers();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Question");
            System.out.println("2. View Questions");
            System.out.println("3. Update Question");
            System.out.println("4. Delete Question & Answers");
            System.out.println("5. Add Answer");
            System.out.println("6. View Answers");
            System.out.println("7. Update Answer");
            System.out.println("8. Delete Answer");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1: // Add Question
                    System.out.print("Enter question: ");
                    String qText = scanner.nextLine().trim();
                    if (!InputValidator.isValidText(qText)) {
                        System.out.println("Error: Question cannot be empty!");
                        break;
                    }
                    Question newQuestion = new Question(qText);
                    questionMap.put(newQuestion.getId(), newQuestion);
                    saveQuestions(questionMap);
                    System.out.println("Added: " + newQuestion);
                    break;

                case 2: // View Questions
                    if (questionMap.isEmpty()) {
                        System.out.println("No questions available.");
                    } else {
                        for (Question q : questionMap.values()) {
                            System.out.println(q);
                        }
                    }
                    break;

                case 3: // Update Question
                    System.out.print("Enter question ID to update: ");
                    String updateQId = scanner.nextLine().trim();
                    if (!InputValidator.isValidText(updateQId)) {
                        System.out.println("Error: Question ID cannot be empty!");
                        break;
                    }
                    if (questionMap.containsKey(updateQId)) {
                        System.out.print("Enter new question text: ");
                        String newQText = scanner.nextLine().trim();
                        if (!InputValidator.isValidText(newQText)) {
                            System.out.println("Error: New question text cannot be empty!");
                            break;
                        }
                        Question qToUpdate = questionMap.get(updateQId);
                        qToUpdate.setText(newQText);
                        saveQuestions(questionMap);
                        System.out.println("Question updated successfully!");
                    } else {
                        System.out.println("Error: Question not found!");
                    }
                    break;

                case 4: // Delete Question & its Answers
                    System.out.print("Enter question ID to delete: ");
                    String deleteQId = scanner.nextLine().trim();
                    if (!InputValidator.isValidText(deleteQId)) {
                        System.out.println("Error: Question ID cannot be empty!");
                        break;
                    }
                    if (questionMap.containsKey(deleteQId)) {
                        questionMap.remove(deleteQId);
                        // Remove all answers associated with this question
                        Iterator<Map.Entry<String, Answer>> it = answerMap.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<String, Answer> entry = it.next();
                            if (entry.getValue().getQuestionId().equals(deleteQId)) {
                                it.remove();
                            }
                        }
                        saveQuestions(questionMap);
                        saveAnswers(answerMap);
                        System.out.println("Question and its associated answers deleted!");
                    } else {
                        System.out.println("Error: Question not found!");
                    }
                    break;

                case 5: // Add Answer
                    System.out.print("Enter question ID for the answer: ");
                    String qId = scanner.nextLine().trim();
                    if (!InputValidator.isValidText(qId)) {
                        System.out.println("Error: Question ID cannot be empty!");
                        break;
                    }
                    if (!questionMap.containsKey(qId)) {
                        System.out.println("Error: Question ID not found!");
                        break;
                    }
                    System.out.print("Enter answer: ");
                    String aText = scanner.nextLine().trim();
                    if (!InputValidator.isValidText(aText)) {
                        System.out.println("Error: Answer cannot be empty!");
                        break;
                    }
                    Answer newAnswer = new Answer(aText, qId);
                    answerMap.put(newAnswer.getId(), newAnswer);
                    saveAnswers(answerMap);
                    System.out.println("Answer added: " + newAnswer);
                    break;

                case 6: // View Answers for a Question
                    System.out.print("Enter question ID to view its answers: ");
                    String qIdForAnswers = scanner.nextLine().trim();
                    if (!InputValidator.isValidText(qIdForAnswers)) {
                        System.out.println("Error: Question ID cannot be empty!");
                        break;
                    }
                    boolean found = false;
                    for (Answer a : answerMap.values()) {
                        if (a.getQuestionId().equals(qIdForAnswers)) {
                            System.out.println(a);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No answers available for this question.");
                    }
                    break;

                case 7: // Update Answer
                    // Debug print: show available answer IDs
                    System.out.println("Available Answer IDs: " + answerMap.keySet());
                    System.out.print("Enter answer ID to update: ");
                    String updateAId = scanner.nextLine().trim();
                    if (!InputValidator.isValidText(updateAId)) {
                        System.out.println("Error: Answer ID cannot be empty!");
                        break;
                    }
                    if (answerMap.containsKey(updateAId)) {
                        System.out.print("Enter new answer text: ");
                        String newAText = scanner.nextLine().trim();
                        if (!InputValidator.isValidText(newAText)) {
                            System.out.println("Error: New answer text cannot be empty!");
                            break;
                        }
                        Answer aToUpdate = answerMap.get(updateAId);
                        aToUpdate.setText(newAText);
                        saveAnswers(answerMap);
                        System.out.println("Answer updated successfully!");
                    } else {
                        System.out.println("Error: Answer not found!");
                    }
                    break;

                case 8: // Delete Answer
                    System.out.print("Enter answer ID to delete: ");
                    String deleteAId = scanner.nextLine().trim();
                    if (!InputValidator.isValidText(deleteAId)) {
                        System.out.println("Error: Answer ID cannot be empty!");
                        break;
                    }
                    if (answerMap.containsKey(deleteAId)) {
                        answerMap.remove(deleteAId);
                        saveAnswers(answerMap);
                        System.out.println("Answer deleted!");
                    } else {
                        System.out.println("Error: Answer not found!");
                    }
                    break;

                case 9: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    // --------------------
    // Persistence Methods
    // --------------------

    private static HashMap<String, Question> loadQuestions() {
        HashMap<String, Question> map = new HashMap<>();
        File file = new File(QUESTIONS_FILE);
        if (!file.exists()) {
            return map;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Each line format: id|question text
                String[] parts = line.split("\\|", 2);
                if (parts.length < 2) continue;
                String id = parts[0];
                String text = parts[1];
                Question q = new Question(id, text);
                map.put(id, q);
            }
        } catch (IOException e) {
            System.out.println("Error reading questions file: " + e.getMessage());
        }
        return map;
    }

    private static void saveQuestions(HashMap<String, Question> questionMap) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(QUESTIONS_FILE))) {
            for (Question q : questionMap.values()) {
                bw.write(q.getId() + "|" + q.getText());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing questions file: " + e.getMessage());
        }
    }

    private static HashMap<String, Answer> loadAnswers() {
        HashMap<String, Answer> map = new HashMap<>();
        File file = new File(ANSWERS_FILE);
        if (!file.exists()) {
            return map;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Each line format: id|questionId|answer text
                String[] parts = line.split("\\|", 3);
                if (parts.length < 3) continue;
                String id = parts[0];
                String questionId = parts[1];
                String text = parts[2];
                Answer a = new Answer(id, text, questionId);
                map.put(id, a);
            }
        } catch (IOException e) {
            System.out.println("Error reading answers file: " + e.getMessage());
        }
        return map;
    }

    private static void saveAnswers(HashMap<String, Answer> answerMap) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ANSWERS_FILE))) {
            for (Answer a : answerMap.values()) {
                bw.write(a.getId() + "|" + a.getQuestionId() + "|" + a.getText());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing answers file: " + e.getMessage());
        }
    }

    // --------------------
    // InputValidator Nested Class
    // --------------------
    static class InputValidator {
        public static boolean isValidText(String text) {
            return text != null && !text.trim().isEmpty();
        }
    }
}
