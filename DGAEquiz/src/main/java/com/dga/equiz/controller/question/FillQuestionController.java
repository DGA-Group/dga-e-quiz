package com.dga.equiz.controller.question;

import com.dga.equiz.model.question.FillQuestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FillQuestionController implements QuestionController{

    //region FXML Reference
    @FXML
    public Label labelQuestion;

    @FXML
    public Label labelContext;

    @FXML
    public Button buttonOption1;

    @FXML
    public Button buttonOption2;

    @FXML
    public Button buttonOption3;

    @FXML
    public Button buttonOption4;
    //endregion

    private FillQuestion fillQuestionModel;

    public void setFillQuestionModel(FillQuestion fillQuestionModel) {
        this.fillQuestionModel = fillQuestionModel;
    }

    public void setupFillQuestion(FillQuestion fillQuestionModel) {
        this.labelQuestion.setText(fillQuestionModel.getQuestion());
        this.labelContext.setText(fillQuestionModel.getContext());
        String[] options = fillQuestionModel.getOptions();
        this.buttonOption1.setText(options[1]);
        this.buttonOption2.setText(options[2]);
        this.buttonOption3.setText(options[3]);
        this.buttonOption4.setText(options[4]);
        setupButtonFunction();
    }

    private void setupButtonFunction() {
        this.buttonOption1.setOnAction((ActionEvent event) -> {
            this.fillQuestionModel.setChosenAnswer((byte) 1);
        });

        this.buttonOption2.setOnAction((ActionEvent event) -> {
            this.fillQuestionModel.setChosenAnswer((byte) 2);
        });

        this.buttonOption3.setOnAction((ActionEvent event) -> {
            this.fillQuestionModel.setChosenAnswer((byte) 3);
        });

        this.buttonOption4.setOnAction((ActionEvent event) -> {
            this.fillQuestionModel.setChosenAnswer((byte) 4);
        });
    }

    @Override
    public boolean isCorrect(){
        return fillQuestionModel.isCorrect();
    }

}
