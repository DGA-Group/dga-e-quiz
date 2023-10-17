package com.dga.equiz.controller.question;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ListeningQuestionController {
    @FXML
    public Label labelQuestion;

    @FXML
    public Button buttonPlaySound;

    @FXML
    public Button buttonOption1;

    @FXML
    public Button buttonOption2;

    private Media sound;
    private MediaPlayer mediaPlayer;

    public void setupListeningQuestion(String question, String audioSrc,
                                       String option1, String option2) {
        this.labelQuestion.setText(question);
        this.sound = new Media(audioSrc);
        this.mediaPlayer = new MediaPlayer(this.sound);
        this.buttonOption1.setText(option1);
        this.buttonOption2.setText(option2);
        this.buttonPlaySound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                playAudio();
            }
        });
    }

    public void playAudio() {
        this.mediaPlayer.stop();
        this.mediaPlayer = new MediaPlayer(this.sound);
        this.mediaPlayer.play();
    }

}
