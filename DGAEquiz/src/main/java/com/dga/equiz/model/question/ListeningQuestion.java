package com.dga.equiz.model.question;

public class ListeningQuestion extends Question {

    private final String audioSrc;                    // Audio URL source.
    private final String[] options = new String[3];   // Options range from 1 to 2.

    public ListeningQuestion(long id, String question, String audioSrc,
                             String option1, String option2, byte correctAnswer) {
        super(id, question, correctAnswer);
        if (correctAnswer < 1 || correctAnswer > 2) {
            throw new IllegalArgumentException("Listening Question Error: Answer must between 1 and 2!");
        }
        this.audioSrc = audioSrc;
        this.options[1] = option1;
        this.options[2] = option2;
    }

    public String getAudioSrc() {
        return audioSrc;
    }

    public String[] getOptions() {
        return options;
    }

    @Override
    public boolean isCorrect() {
        return (this.correctAnswer == this.chosenAnswer);
    }
}
