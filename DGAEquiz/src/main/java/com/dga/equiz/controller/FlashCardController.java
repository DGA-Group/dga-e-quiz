package com.dga.equiz.controller;

import com.dga.equiz.model.FlashCard;
import com.dga.equiz.model.PairWord;
import com.dga.equiz.model.Profile;
import com.dga.equiz.utils.ApplicationData;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.dga.equiz.model.FlashCard.ListFlashCard;

public class FlashCardController implements Initializable {

    @FXML
    private StackPane FlashCardPane;

    @FXML
    private Button prevButton;

    @FXML
    private Button nextButton;

    @FXML
    private Label wordLabel;

    @FXML
    private Label curLabel;

    @FXML
    private Label atlertLabel;

    @FXML
    private Label titleLabel;

    private boolean isFront = true;
    private String selected = "";
    private int index = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Profile profile = ApplicationData.getInstance().profile;
        titleLabel.setVisible(true);

        try {
            ListFlashCard = (ArrayList<PairWord>) FlashCard.getListFlashCard();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (!ListFlashCard.isEmpty()) {
            atlertLabel.setVisible(false);
            wordLabel.setText(ListFlashCard.get(0).getWordName());
            curLabel.setText(index + " / " + ListFlashCard.size());
            FlashCardPane.setOnMouseClicked(event -> {
                wordLabel.setVisible(false);
                rotatePane();
            });
            if (index == 1) {
                prevButton.setVisible(false);
            } else {
                prevButton.setVisible(true);
            }

            nextButton.setOnMouseClicked(event -> {
                if (index < ListFlashCard.size()) {
                    isFront = true;
                    right_trans();
                    index++;
                    showButton(index);
                    selected = ListFlashCard.get(index - 1).getWordName();
                    wordLabel.setText(selected);
                    curLabel.setText(index + " / " + ListFlashCard.size());
                }
            });
            prevButton.setOnMouseClicked(event -> {
                if (index > 1) {
                    isFront = true;
                    left_trans();
                    index--;
                    showButton(index);
                    selected = ListFlashCard.get(index - 1).getWordName();
                    wordLabel.setText(selected);
                    curLabel.setText(index + " / " + ListFlashCard.size());
                }
            });
        } else {
            atlertLabel.setVisible(true);
            prevButton.setVisible(false);
            nextButton.setVisible(false);
            curLabel.setVisible(false);
            wordLabel.setVisible(false);
        }

    }

    public void rotatePane() {

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.3), FlashCardPane);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(180);
        rotateTransition.play();
        RotateTransition rotate = new RotateTransition(Duration.seconds(0.3), wordLabel);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.setFromAngle(0);
        rotate.setToAngle(180);
        rotate.play();
        rotate.setOnFinished(e -> {
            if (isFront) {
                selected = wordLabel.getText();
                wordLabel.setText(ListFlashCard.get(index - 1).getMeaning());
            } else {
                wordLabel.setText(selected);
            }
            wordLabel.setVisible(true);
            isFront = !isFront;
        });
    }

    private void fadeTrans() {
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), FlashCardPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
    private void left_trans() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), FlashCardPane);
        translateTransition.setFromX(-FlashCardPane.getWidth());
        translateTransition.setToX(0);
        translateTransition.setAutoReverse(true);
        fadeTrans();
        if (!isFront) {
            selected = wordLabel.getText();
            wordLabel.setText(ListFlashCard.get(index - 1).getMeaning());
        } else {
            wordLabel.setText(selected);
        }
        translateTransition.play();
    }
    private void right_trans() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), FlashCardPane);
        translateTransition.setFromX(FlashCardPane.getWidth());
        translateTransition.setToX(0);
        translateTransition.setAutoReverse(true);
        fadeTrans();
        if (!isFront) {
            selected = wordLabel.getText();
            wordLabel.setText(ListFlashCard.get(index - 1).getMeaning());
        } else {
            wordLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");
            wordLabel.setText(selected);
        }
        translateTransition.play();
    }

    public void showButton(int pt) {
        if (pt == 1) {
            prevButton.setVisible(false);
        } else {
            prevButton.setVisible(true);
        }

        if (pt == ListFlashCard.size()) {
            nextButton.setVisible(false);
        } else {
            nextButton.setVisible(true);
        }
    }

}

