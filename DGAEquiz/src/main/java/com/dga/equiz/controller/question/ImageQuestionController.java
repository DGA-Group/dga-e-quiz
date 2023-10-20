package com.dga.equiz.controller.question;

import com.dga.equiz.model.question.ImageQuestion;
import javafx.event.ActionEvent;
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

    private ImageQuestion imageQuestionModel;

    public void setImageQuestionModel(ImageQuestion imageQuestionModel) {
        this.imageQuestionModel = imageQuestionModel;
    }

    public void setupImageQuestion(ImageQuestion imageQuestionModel) {
        this.labelQuestion.setText(imageQuestionModel.getQuestion());
        this.imageView.setImage(new Image(imageQuestionModel.getImageSrc()));
        String[] options = imageQuestionModel.getOptions();
        this.buttonOption1.setText(options[1]);
        this.buttonOption2.setText(options[2]);
        this.buttonOption3.setText(options[3]);
        this.buttonOption4.setText(options[4]);
        setupButtonFunction();
    }

    private void setupButtonFunction() {
        this.buttonOption1.setOnAction((ActionEvent event) -> {
            this.imageQuestionModel.setChosenAnswer((byte) 1);
        });

        this.buttonOption2.setOnAction((ActionEvent event) -> {
            this.imageQuestionModel.setChosenAnswer((byte) 2);
        });

        this.buttonOption3.setOnAction((ActionEvent event) -> {
            this.imageQuestionModel.setChosenAnswer((byte) 3);
        });

        this.buttonOption4.setOnAction((ActionEvent event) -> {
            this.imageQuestionModel.setChosenAnswer((byte) 4);
        });
    }

}
