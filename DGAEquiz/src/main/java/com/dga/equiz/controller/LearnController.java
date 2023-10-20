package com.dga.equiz.controller;

import com.dga.equiz.controller.question.FillQuestionController;
import com.dga.equiz.controller.question.ImageQuestionController;
import com.dga.equiz.controller.question.ListeningQuestionController;
import com.dga.equiz.controller.question.TranslateQuestionController;
import com.dga.equiz.model.Lesson;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.model.question.*;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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

    public void setLesson(Lesson lesson) {
        panelQuestion.getChildren().removeAll();
        List<Question> questions = lesson.getQuestions();
        for (var question : questions) {
            if (question instanceof ImageQuestion) {
                addImageQuestion(question.getId());
            }

            if (question instanceof FillQuestion) {
                addFillQuestion(question.getId());
            }

            if (question instanceof ListeningQuestion) {
                addListeningQuestion(question.getId());
            }

            if (question instanceof TranslateQuestion) {
                addTranslateQuestion(question.getId());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttonContinue.setOnAction((ActionEvent event) -> {

        });

    }

    private void addImageQuestion(long id) {
        try {
            var imageQuestionData = ApplicationData.getInstance().getImageQuestionData();
            var imageQuestion = imageQuestionData.get(id);
            if(imageQuestion == null) {
                System.out.println("Image Question is null!");
                return;
            }

            NodeObject newQuestion = EquizUtils.Instantiate("/view/question/ImageQuestionView.fxml", panelQuestion);

            ImageQuestionController controller = newQuestion.getController();
            controller.setImageQuestionModel(imageQuestion);
            controller.setupImageQuestion(imageQuestion);

            newQuestion.hide();
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("Unable to instantiate image question!");
            System.out.println("======================================");
            e.printStackTrace();
        }
    }

    private void addListeningQuestion(long id) {
        try {
            var listeningQuestionData = ApplicationData.getInstance().getListeningQuestionData();
            var listeningQuestion = listeningQuestionData.get(id);
            if(listeningQuestion == null) {
                System.out.println("Listening Question is null!");
                return;
            }

            NodeObject newQuestion = EquizUtils.Instantiate("/view/question/ListeningQuestionView.fxml", panelQuestion);

            ListeningQuestionController controller = newQuestion.getController();
            controller.setListeningQuestionModel(listeningQuestion);
            controller.setupListeningQuestion(listeningQuestion);

            newQuestion.hide();
        } catch (Exception e) {
            System.out.println("=========================================");
            System.out.println("Unable to instantiate listening question!");
            System.out.println("=========================================");
            e.printStackTrace();
        }
    }

    private void addFillQuestion(long id) {
        try {
            var fillQuestionData = ApplicationData.getInstance().getFillQuestionData();
            var fillQuestion = fillQuestionData.get(id);
            if(fillQuestion == null) {
                System.out.println("Fill Question is null!");
                return;
            }

            NodeObject newQuestion = EquizUtils.Instantiate("/view/question/FillQuestionView.fxml", panelQuestion);

            FillQuestionController controller = newQuestion.getController();
            controller.setFillQuestionModel(fillQuestion);
            controller.setupFillQuestion(fillQuestion);

            newQuestion.hide();
        } catch (Exception e) {
            System.out.println("====================================");
            System.out.println("Unable to instantiate fill question!");
            System.out.println("====================================");
            e.printStackTrace();
        }
    }

    private void addTranslateQuestion(long id) {
        try {
            var translateQuestionData = ApplicationData.getInstance().getTranslateQuestionData();
            var translateQuestion = translateQuestionData.get(id);
            if(translateQuestion == null) {
                System.out.println("Translate Question is null!");
                return;
            }

            NodeObject newQuestion = EquizUtils.Instantiate("/view/question/TranslateQuestionView.fxml", panelQuestion);

            TranslateQuestionController controller = newQuestion.getController();
            controller.setTranslateQuestionModel(translateQuestion);
            controller.setupTranslateQuestion(translateQuestion);

            newQuestion.hide();
        } catch (Exception e) {
            System.out.println("=========================================");
            System.out.println("Unable to instantiate translate question!");
            System.out.println("=========================================");
            e.printStackTrace();
        }
    }
}
