package com.dga.equiz.model.question;

public class TranslateQuestion extends Question {

    private String context;
    private String[] options = new String[5];

    public TranslateQuestion(long id, String question, String context, String option1,
                             String option2, String option3, String option4, byte correctAnswer) {
        super(id, question, correctAnswer);
        if (correctAnswer < 1 || correctAnswer > 4) {
            throw new IllegalArgumentException("Translate Question Error: Answer must between 1 and 4!");
        }
        this.context = context;
        this.options[1] = option1;
        this.options[2] = option2;
        this.options[3] = option3;
        this.options[4] = option4;
    }

    public String getContext() {
        return context;
    }

    public String[] getOptions() {
        return options;
    }

    @Override
    public void setChosenAnswer(byte chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

    @Override
    public boolean isCorrect() {
        return (this.correctAnswer == this.chosenAnswer);
    }
}
