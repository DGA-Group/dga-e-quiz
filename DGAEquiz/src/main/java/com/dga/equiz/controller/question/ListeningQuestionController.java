package com.dga.equiz.controller.question;

import com.dga.equiz.model.question.ListeningQuestion;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ListeningQuestionController implements QuestionController{
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
    public Button currentButton;
    public Button buttonSubmit;

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
            this.buttonSubmit.setDisable(false);
            changeChosenButtonStyle(this.buttonOption1);
        });

        this.buttonOption2.setOnAction((ActionEvent event) -> {
            this.listeningQuestionModel.setChosenAnswer((byte) 2);
            this.buttonSubmit.setDisable(false);
            changeChosenButtonStyle(this.buttonOption2);
        });

        this.buttonPlaySound.setOnAction((ActionEvent event) -> {
            playAudio();
        });
    }

    public void playAudio() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
        }
        this.mediaPlayer = new MediaPlayer(this.sound);
        this.mediaPlayer.play();
    }

    private void changeChosenButtonStyle(Button button) {
        if(this.currentButton != null){
            EquizUtils.setStyle(this.currentButton, "learn-button");
        }
        this.currentButton = button;
        EquizUtils.setStyle(this.currentButton, "button-correct-answer");
    }

    @Override
    public boolean isCorrect(){
        return listeningQuestionModel.isCorrect();
    }

    @Override
    public void handleWrongAnswer() {
        EquizUtils.setStyle(this.currentButton, "button-wrong-answer");
    }

    @Override
    public void handleCorrectAnswer() {

    }

    @Override
    public void resetChosenAnswer() {
        this.listeningQuestionModel.setChosenAnswer((byte) -1);
        EquizUtils.setStyle(this.currentButton, "learn-button");
    }

}
