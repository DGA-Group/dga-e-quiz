package com.dga.equiz.model.question;

public class ImageQuestion extends Question {

    private final String imageSrc;                    // Image URL source.
    private final String[] options = new String[5];   // Options range from 1 to 4.

    public ImageQuestion(long id, String question, String imageSrc, String option1,
                         String option2, String option3, String option4, byte correctAnswer) {
        super(id, question, correctAnswer);
        if (correctAnswer < 1 || correctAnswer > 4) {
            throw new IllegalArgumentException("Image Question Error: Answer must between 1 and 4!");
        }
        this.imageSrc = imageSrc;
        this.options[1] = option1;
        this.options[2] = option2;
        this.options[3] = option3;
        this.options[4] = option4;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public String[] getOptions() {
        return options;
    }

    @Override
    public boolean isCorrect() {
        return (this.correctAnswer == this.chosenAnswer);
    }
}
