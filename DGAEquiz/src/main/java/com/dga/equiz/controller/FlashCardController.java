package com.dga.equiz.controller;

import com.dga.equiz.model.PairWord;
import com.dga.equiz.model.Profile;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.ControllerManager;
import com.dga.equiz.utils.DBHelper;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    private boolean isFront = true;
    private String selected = "";
    private int index = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManager.getInstance().flashCardController = this;
        atlertLabel.setVisible(true);
        prevButton.setVisible(false);
        nextButton.setVisible(false);
        curLabel.setVisible(false);
        wordLabel.setVisible(false);
        initFlashCard();
    }

    private void initFlashCard() {
        Profile profile = ApplicationData.getInstance().profile;
        ArrayList<PairWord> flashCards = profile.getFlashCards();
        nextButton.setOnMouseClicked(event -> {
            if (index < flashCards.size()) {
                isFront = true;
                right_trans(flashCards);
                index++;
                showButton(flashCards);
                selected = flashCards.get(index - 1).getWordName();
                wordLabel.setText(selected);
                curLabel.setText(index + " / " + flashCards.size());
            }
        });

        prevButton.setOnMouseClicked(event -> {
            if (index > 1) {
                isFront = true;
                left_trans(flashCards);
                index--;
                showButton(flashCards);
                selected = flashCards.get(index - 1).getWordName();
                wordLabel.setText(selected);
                curLabel.setText(index + " / " + flashCards.size());
            }
        });

        reloadFlashCard();
    }

    public void rotatePane(ArrayList<PairWord> flashCards) {
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
                wordLabel.setText(flashCards.get(index - 1).getMeaning());
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

    private void left_trans(ArrayList<PairWord> flashCards) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), FlashCardPane);
        translateTransition.setFromX(-FlashCardPane.getWidth());
        translateTransition.setToX(0);
        translateTransition.setAutoReverse(true);
        fadeTrans();
        if (!isFront) {
            selected = wordLabel.getText();
            wordLabel.setText(flashCards.get(index - 1).getMeaning());
        } else {
            wordLabel.setText(selected);
        }
        translateTransition.play();
    }

    private void right_trans(ArrayList<PairWord> flashCards) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), FlashCardPane);
        translateTransition.setFromX(FlashCardPane.getWidth());
        translateTransition.setToX(0);
        translateTransition.setAutoReverse(true);
        fadeTrans();
        if (!isFront) {
            selected = wordLabel.getText();
            wordLabel.setText(flashCards.get(index - 1).getMeaning());
        } else {
            wordLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");
            wordLabel.setText(selected);
        }
        translateTransition.play();
    }

    public void showButton(ArrayList<PairWord> flashCards) {
        prevButton.setVisible(index != 1);
        nextButton.setVisible(index != flashCards.size());
    }

    public void reloadFlashCard() {
        Profile profile = ApplicationData.getInstance().profile;
        HomeController homeController = ControllerManager.getInstance().homeController;
        ArrayList<PairWord> flashCards = profile.getFlashCards();
        if (!flashCards.isEmpty()) {
            atlertLabel.setVisible(false);
            wordLabel.setVisible(true);
            wordLabel.setText(flashCards.get(0).getWordName());
            curLabel.setText(index + " / " + flashCards.size());
            FlashCardPane.setOnMouseClicked(event -> {
                wordLabel.setVisible(false);
                rotatePane(flashCards);
            });
        }
        showButton(flashCards);
        if (homeController != null) {
            homeController.setFlashCard(String.valueOf(flashCards.size()));
        }
    }

    public int findUserFlashCards(int id) {
        String sql = "SELECT COUNT(*) as flashcard_count FROM flashcard WHERE id ='" + id + "';";
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {
            resultSet = DBHelper.executeQuery(sql);
            statement = resultSet.getStatement();
            connection = statement.getConnection();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBHelper.closeQuery(resultSet, statement, connection);
            } catch (Exception ignore) {
            }
        }
        return 0;
    }

}

