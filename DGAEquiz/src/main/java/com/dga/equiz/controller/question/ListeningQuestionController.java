package com.dga.equiz.controller.question;

import com.dga.equiz.model.question.ImageQuestion;
import com.dga.equiz.model.question.ListeningQuestion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ListeningQuestionController {
    //region FXML Reference
    @FXML
    public Label labelQuestion;

    @FXML
    public Button buttonPlaySound;

    @FXML
    public Button buttonOption1;

    @FXML
    public Button buttonOption2;
    //endregion

    private Media sound;
    private MediaPlayer mediaPlayer;
    private ListeningQuestion listeningQuestionModel;

    public void setListeningQuestionModel(ListeningQuestion listeningQuestionModel) {
        this.listeningQuestionModel = listeningQuestionModel;
    }

    public void setupListeningQuestion(ListeningQuestion listeningQuestionModel) {
        this.labelQuestion.setText(listeningQuestionModel.getQuestion());
        this.sound = new Media(listeningQuestionModel.getAudioSrc());
        String[] options = listeningQuestionModel.getOptions();
        this.buttonOption1.setText(options[1]);
        this.buttonOption2.setText(options[2]);
        setupButtonFunction();
    }

    private void setupButtonFunction() {
        this.buttonOption1.setOnAction((ActionEvent event) -> {
            this.listeningQuestionModel.setChosenAnswer((byte) 1);
        });

        this.buttonOption2.setOnAction((ActionEvent event) -> {
            this.listeningQuestionModel.setChosenAnswer((byte) 2);
        });

        this.buttonPlaySound.setOnAction((ActionEvent event) -> {
            playAudio();
        });
    }

    public void playAudio() {
        this.mediaPlayer.stop();
        this.mediaPlayer = new MediaPlayer(this.sound);
        this.mediaPlayer.play();
    }

}
