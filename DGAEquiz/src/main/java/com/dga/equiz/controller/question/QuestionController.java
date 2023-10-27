package com.dga.equiz.controller.question;

public interface QuestionController {
    boolean isCorrect();
    void handleWrongAnswer();
    void handleCorrectAnswer();
    void resetChosenAnswer();
}
