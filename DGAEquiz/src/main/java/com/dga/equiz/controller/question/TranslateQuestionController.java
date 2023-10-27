package com.dga.equiz.controller.question;

import com.dga.equiz.model.question.TranslateQuestion;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TranslateQuestionController implements QuestionController{
    //region FXML Reference
    @FXML
    public Label labelQuestion;

    @FXML
    public Label labelWord;

    @FXML
    public Button buttonOption1;

    @FXML
    public Button buttonOption2;

    @FXML
    public Button buttonOption3;

    @FXML
    public Button buttonOption4;
    //endregion

    private TranslateQuestion translateQuestionModel;
    public Button currentButton;
    public Button buttonSubmit;

    public void setTranslateQuestionModel(TranslateQuestion translateQuestionModel) {
        this.translateQuestionModel = translateQuestionModel;
    }

    public void setupTranslateQuestion(TranslateQuestion translateQuestionModel) {
        this.labelQuestion.setText(translateQuestionModel.getQuestion());
        this.labelWord.setText(translateQuestionModel.getContext());
        String[] options = translateQuestionModel.getOptions();
        this.buttonOption1.setText(options[1]);
        this.buttonOption2.setText(options[2]);
        this.buttonOption3.setText(options[3]);
        this.buttonOption4.setText(options[4]);
        setupButtonFunction();
    }

    private void setupButtonFunction() {
        this.buttonOption1.setOnAction((ActionEvent event) -> {
            this.translateQuestionModel.setChosenAnswer((byte) 1);
            this.buttonSubmit.setDisable(false);
            changeChosenButtonStyle(this.buttonOption1);
        });

        this.buttonOption2.setOnAction((ActionEvent event) -> {
            this.translateQuestionModel.setChosenAnswer((byte) 2);
            this.buttonSubmit.setDisable(false);
            changeChosenButtonStyle(this.buttonOption2);
        });

        this.buttonOption3.setOnAction((ActionEvent event) -> {
            this.translateQuestionModel.setChosenAnswer((byte) 3);
            this.buttonSubmit.setDisable(false);
            changeChosenButtonStyle(this.buttonOption3);
        });

        this.buttonOption4.setOnAction((ActionEvent event) -> {
            this.translateQuestionModel.setChosenAnswer((byte) 4);
            this.buttonSubmit.setDisable(false);
            changeChosenButtonStyle(this.buttonOption4);
        });
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
        return translateQuestionModel.isCorrect();
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
        this.translateQuestionModel.setChosenAnswer((byte) -1);
        EquizUtils.setStyle(this.currentButton, "learn-button");
    }

}
