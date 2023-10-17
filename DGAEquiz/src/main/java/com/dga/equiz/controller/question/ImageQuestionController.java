package com.dga.equiz.controller.question;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageQuestionController {
    //region FXML Reference
    @FXML
    public Label labelQuestion;

    @FXML
    public ImageView imageView;

    @FXML
    public Button buttonOption1;

    @FXML
    public Button buttonOption2;

    @FXML
    public Button buttonOption3;

    @FXML
    public Button buttonOption4;
    //endregion

    public void setupImageQuestion(String question, String imageSrc, String option1,
                                   String option2, String option3, String option4) {
        this.labelQuestion.setText(question);
        this.imageView.setImage(new Image(imageSrc));
        this.buttonOption1.setText(option1);
        this.buttonOption2.setText(option2);
        this.buttonOption3.setText(option3);
        this.buttonOption4.setText(option4);
    }

}
