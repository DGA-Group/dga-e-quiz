package com.dga.equiz.controller.question;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FillQuestionController {
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

    public void setupFillQuestion(String question, String context, String option1,
                             String option2, String option3, String option4) {
        this.labelQuestion.setText(question);
        this.labelContext.setText(context);
        this.buttonOption1.setText(option1);
        this.buttonOption2.setText(option2);
        this.buttonOption3.setText(option3);
        this.buttonOption4.setText(option4);
    }

}
