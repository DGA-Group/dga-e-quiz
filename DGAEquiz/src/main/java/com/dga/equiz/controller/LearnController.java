package com.dga.equiz.controller;

import com.dga.equiz.controller.question.FillQuestionController;
import com.dga.equiz.controller.question.ImageQuestionController;
import com.dga.equiz.controller.question.ListeningQuestionController;
import com.dga.equiz.controller.question.TranslateQuestionController;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.model.question.TranslateQuestion;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LearnController implements Initializable {
    //region FXML Reference
    @FXML
    public ProgressBar pgbarLessonProgress;

    @FXML
    public Button buttonClose;

    @FXML
    public AnchorPane panelQuestion;

    @FXML
    public Label labelComment;

    @FXML
    public Button buttonSubmit;

    @FXML
    public Button buttonContinue;
    //endregion

    private List<NodeObject> listQuestion = new ArrayList<NodeObject>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addImageQuestion();
        addListeningQuestion();
        addFillQuestion();
        addTranslateQuestion();
    }

    private void addImageQuestion() {
        try {
            var imageQuestionData = ApplicationData.getInstance().getImageQuestionData();
            for (var imageQuestion : imageQuestionData.values()) {
                NodeObject newQuestion = EquizUtils.Instantiate("/view/question/ImageQuestionView.fxml");

                ImageQuestionController controller = newQuestion.getController();
                String question = imageQuestion.getQuestion();
                String imageSrc = imageQuestion.getImageSrc();
                String[] options = imageQuestion.getOptions();
                controller.setupImageQuestion(question, imageSrc, options[1], options[2], options[3], options[4]);

                EventHandler<ActionEvent> closeQuestion = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        newQuestion.hide();
                    }
                };

                controller.buttonOption1.setOnAction(closeQuestion);
                controller.buttonOption2.setOnAction(closeQuestion);
                controller.buttonOption3.setOnAction(closeQuestion);
                controller.buttonOption4.setOnAction(closeQuestion);

                panelQuestion.getChildren().add(newQuestion.getNode());
                newQuestion.show();
            }
        } catch (Exception e) {
            System.out.println("===========================================");
            System.out.println("Unable to add image question to learn view!");
            System.out.println("===========================================");
            e.printStackTrace();
        }

    }

    private void addListeningQuestion() {
        try {
            var listeningQuestionData = ApplicationData.getInstance().getListeningQuestionData();
            for (var listeningQuestion : listeningQuestionData.values()) {
                NodeObject newQuestion = EquizUtils.Instantiate("/view/question/ListeningQuestionView.fxml");

                String question = listeningQuestion.getQuestion();
                String audioSrc = listeningQuestion.getAudioSrc();
                String[] options = listeningQuestion.getOptions();
                ListeningQuestionController controller = newQuestion.getController();
                controller.setupListeningQuestion(question, audioSrc, options[1], options[2]);

                EventHandler<ActionEvent> closeQuestion = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        newQuestion.hide();
                    }
                };

                controller.buttonOption1.setOnAction(closeQuestion);
                controller.buttonOption2.setOnAction(closeQuestion);

                panelQuestion.getChildren().add(newQuestion.getNode());
                newQuestion.show();
            }
        } catch (Exception e) {
            System.out.println("===============================================");
            System.out.println("Unable to add listening question to learn view!");
            System.out.println("===============================================");
            e.printStackTrace();
        }
    }

    private void addFillQuestion() {
        try {
            var fillQuestionData = ApplicationData.getInstance().getFillQuestionData();
            for (var fillQuestion : fillQuestionData.values()) {
                NodeObject newQuestion = EquizUtils.Instantiate("/view/question/FillQuestionView.fxml");

                String question = fillQuestion.getQuestion();
                String context = fillQuestion.getContext();
                String[] options = fillQuestion.getOptions();
                FillQuestionController controller = newQuestion.getController();
                controller.setupFillQuestion(question, context, options[1], options[2], options[3], options[4]);

                EventHandler<ActionEvent> closeQuestion = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        newQuestion.hide();
                    }
                };

                controller.buttonOption1.setOnAction(closeQuestion);
                controller.buttonOption2.setOnAction(closeQuestion);
                controller.buttonOption3.setOnAction(closeQuestion);
                controller.buttonOption4.setOnAction(closeQuestion);

                panelQuestion.getChildren().add(newQuestion.getNode());
                newQuestion.show();
            }
        } catch (Exception e) {
            System.out.println("=========================================");
            System.out.println("Unable to add fill question to learn view!");
            System.out.println("=========================================");
            e.printStackTrace();
        }
    }

    private void addTranslateQuestion() {
        try {
            var translateQuestionData = ApplicationData.getInstance().getTranslateQuestionData();
            for (var translateQuestion : translateQuestionData.values()) {
                NodeObject newQuestion = EquizUtils.Instantiate("/view/question/TranslateQuestionView.fxml");

                String question = translateQuestion.getQuestion();
                String context = translateQuestion.getContext();
                String[] options = translateQuestion.getOptions();
                TranslateQuestionController controller = newQuestion.getController();
                controller.setupTranslateQuestion(question, context, options[1], options[2], options[3], options[4]);

                EventHandler<ActionEvent> closeQuestion = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        newQuestion.hide();
                    }
                };

                controller.buttonOption1.setOnAction(closeQuestion);
                controller.buttonOption2.setOnAction(closeQuestion);
                controller.buttonOption3.setOnAction(closeQuestion);
                controller.buttonOption4.setOnAction(closeQuestion);

                panelQuestion.getChildren().add(newQuestion.getNode());
                newQuestion.show();
            }
        } catch (Exception e) {
            System.out.println("===============================================");
            System.out.println("Unable to add translate question to learn view!");
            System.out.println("===============================================");
            e.printStackTrace();
        }
    }
}
