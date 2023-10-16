package com.dga.equiz.model.question;

public abstract class Question {

    protected final long id;            // Question id.
    protected final String question;    // Question content.
    protected final byte correctAnswer; // Answer range from 1 to 4.
    protected byte chosenAnswer;

    public Question(long id, String question, byte correctAnswer) {
        this.id = id;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public byte getCorrectAnswer() {
        return correctAnswer;
    }

    public abstract boolean isCorrect();
}
