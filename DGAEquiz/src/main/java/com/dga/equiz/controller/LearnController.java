package com.dga.equiz.controller;

import com.dga.equiz.controller.question.*;
import com.dga.equiz.model.Lesson;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class LearnController implements Initializable {
    //region FXML Reference
    @FXML
    public ProgressBar pgbarLessonProgress;

    @FXML
    public Button buttonClose;

    @FXML
    public StackPane paneQuestion;

    @FXML
    public Label labelComment;

    @FXML
    public Button buttonSubmit;

    @FXML
    public Button buttonContinue;
    //endregion

    private NodeObject currentQuestion;
    private LinkedList<NodeObject> linkedListQuestions = new LinkedList<NodeObject>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttonContinue.setOnAction((ActionEvent event) -> {
            nextQuestion();
            switchButton();
        });

        this.buttonSubmit.setOnAction((ActionEvent event) -> {
            checkAnswer();
            switchButton();
        });

    }

    public void setLesson(Lesson lesson) {
        linkedListQuestions.clear();
        paneQuestion.getChildren().removeAll();
        List<Long> image_questions_id = lesson.getImage_questions_id();
        for (var questionId : image_questions_id) {
            addImageQuestion(questionId);
        }
        List<Long> fill_questions_id = lesson.getFill_questions_id();
        for (var questionId : fill_questions_id) {
            addFillQuestion(questionId);
        }
        List<Long> listening_questions_id = lesson.getListening_questions_id();
        for (var questionId : listening_questions_id) {
            addListeningQuestion(questionId);
        }
        List<Long> translate_questions_id = lesson.getTranslate_questions_id();
        for (var questionId : translate_questions_id) {
            addTranslateQuestion(questionId);
        }

        if (linkedListQuestions.isEmpty()) {
            System.out.println("There is no question in this campaign!");
        } else {
            // Show the first question.
            Collections.shuffle(linkedListQuestions);
            currentQuestion = linkedListQuestions.getFirst();
            currentQuestion.show();
        }
    }

    private void addImageQuestion(long id) {
        try {
            var imageQuestionData = ApplicationData.getInstance().getImageQuestionData();
            var imageQuestion = imageQuestionData.get(id);
            if (imageQuestion == null) {
                System.out.println("Image Question is null!");
                return;
            }

            NodeObject newQuestion = EquizUtils.Instantiate("/view/question/ImageQuestionView.fxml", paneQuestion);

            ImageQuestionController controller = newQuestion.getController();
            controller.setImageQuestionModel(imageQuestion);
            controller.setupImageQuestion(imageQuestion);

            newQuestion.hide();
            linkedListQuestions.add(newQuestion);
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
            if (listeningQuestion == null) {
                System.out.println("Listening Question is null!");
                return;
            }

            NodeObject newQuestion = EquizUtils.Instantiate("/view/question/ListeningQuestionView.fxml", paneQuestion);

            ListeningQuestionController controller = newQuestion.getController();
            controller.setListeningQuestionModel(listeningQuestion);
            controller.setupListeningQuestion(listeningQuestion);

            newQuestion.hide();
            linkedListQuestions.add(newQuestion);
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
            if (fillQuestion == null) {
                System.out.println("Fill Question is null!");
                return;
            }

            NodeObject newQuestion = EquizUtils.Instantiate("/view/question/FillQuestionView.fxml", paneQuestion);

            FillQuestionController controller = newQuestion.getController();
            controller.setFillQuestionModel(fillQuestion);
            controller.setupFillQuestion(fillQuestion);

            newQuestion.hide();
            linkedListQuestions.add(newQuestion);
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
            if (translateQuestion == null) {
                System.out.println("Translate Question is null!");
                return;
            }

            NodeObject newQuestion = EquizUtils.Instantiate("/view/question/TranslateQuestionView.fxml", paneQuestion);

            TranslateQuestionController controller = newQuestion.getController();
            controller.setTranslateQuestionModel(translateQuestion);
            controller.setupTranslateQuestion(translateQuestion);

            newQuestion.hide();
            linkedListQuestions.add(newQuestion);
        } catch (Exception e) {
            System.out.println("=========================================");
            System.out.println("Unable to instantiate translate question!");
            System.out.println("=========================================");
            e.printStackTrace();
        }
    }

    private void nextQuestion() {
        if (linkedListQuestions.isEmpty()) {
            return;
        }

        currentQuestion.hide();
        linkedListQuestions.removeFirst();

        if (linkedListQuestions.isEmpty()) {
            System.out.println("Chuc mung ban da hoan thanh khoa hoc!");
            return;
        }

        currentQuestion = linkedListQuestions.getFirst();
        currentQuestion.show();
    }

    private void checkAnswer() {
        QuestionController controller = currentQuestion.getController();
        if (!controller.isCorrect()) {
            linkedListQuestions.addLast(currentQuestion);
            System.out.println("Sai roi");
            return;
        }

        System.out.println("Dung roi");
    }

    private void switchButton() {
        boolean toggleButtonContinue = !this.buttonContinue.isVisible();
        boolean toggleButtonSubmit = !this.buttonSubmit.isVisible();

        this.buttonContinue.setVisible(toggleButtonContinue);
        this.buttonSubmit.setVisible(toggleButtonSubmit);
    }
}
