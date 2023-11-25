package com.dga.equiz.controller;

import com.dga.equiz.controller.question.*;
import com.dga.equiz.model.Lesson;
import com.dga.equiz.model.event.IEvent;
import com.dga.equiz.model.event.IEventLong;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.EquizUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.*;

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

    @FXML
    public AnchorPane paneMessageHolder;

    @FXML
    public Label labelPercent;

    //endregion

    public IEventLong onFinishCampaign;
    public IEvent onGoToFinishView;

    private NodeObject currentQuestion;
    private LinkedList<NodeObject> linkedListQuestions = new LinkedList<NodeObject>();
    private double totalQuestionCount = 0;
    private long campaignId = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttonContinue.setOnAction((ActionEvent event) -> {
            nextQuestion();
            switchButton();
            updateView();
        });

        this.buttonSubmit.setOnAction((ActionEvent event) -> {
            checkAnswer();
            switchButton();
            updateView();
        });

    }

    public void setLesson(Lesson lesson, long campaignId) {
        Platform.runLater(() -> {
            linkedListQuestions.clear();
            paneQuestion.getChildren().clear();
            this.campaignId = campaignId;
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
                return;
            }

            // Reset variable
            totalQuestionCount = linkedListQuestions.size();

            // Reset view
            paneMessageHolder.setVisible(false);
            buttonSubmit.setDisable(true);
            buttonSubmit.setVisible(true);
            buttonContinue.setVisible(false);
            pgbarLessonProgress.setProgress(0);
            labelPercent.setText("0%");

            // Show the first question.
            Collections.shuffle(linkedListQuestions);
            currentQuestion = linkedListQuestions.getFirst();
            currentQuestion.show();
        });

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
            controller.buttonSubmit = this.buttonSubmit;

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
            controller.buttonSubmit = this.buttonSubmit;

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
            controller.buttonSubmit = this.buttonSubmit;

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
            controller.buttonSubmit = this.buttonSubmit;

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
            System.out.println("Chuc mung ban da hoan thanh khoa hoc!");
            if (onFinishCampaign != null) {
                onFinishCampaign.hande(campaignId);
            }
            if (onGoToFinishView != null) {
                onGoToFinishView.handle();
            }
            return;
        }

        QuestionController controller = currentQuestion.getController();
        controller.resetChosenAnswer();
        buttonSubmit.setDisable(true);

        currentQuestion.hide();
        currentQuestion = linkedListQuestions.getFirst();
        currentQuestion.show();
    }

    private void checkAnswer() {
        QuestionController controller = currentQuestion.getController();

        if (controller.isCorrect()) {
            handleCorrectAnswer();
        } else {
            handleWrongAnswer();
        }

        if (!linkedListQuestions.isEmpty()) {
            linkedListQuestions.removeFirst();
        }
        updateView();
    }

    private void handleCorrectAnswer() {
        EquizUtils.setStyle(paneMessageHolder, "message-pane-correct", "message-border");
        labelComment.setText("Đúng rùi bạn nhỏ, cố gắng lên nữa he!");
    }

    private void handleWrongAnswer() {
        EquizUtils.setStyle(paneMessageHolder, "message-pane-wrong", "message-border");
        labelComment.setText("Ầu nầu sai rồi, hãy làm lại nhé!");
        linkedListQuestions.addLast(currentQuestion);

        QuestionController controller = currentQuestion.getController();
        controller.handleWrongAnswer();
    }

    private void switchButton() {
        boolean toggleButtonContinue = !this.buttonContinue.isVisible();
        boolean toggleButtonSubmit = !this.buttonSubmit.isVisible();

        this.buttonContinue.setVisible(toggleButtonContinue);
        this.buttonSubmit.setVisible(toggleButtonSubmit);

        boolean panelMessageVisible = this.paneMessageHolder.isVisible();
        this.paneMessageHolder.setVisible(!panelMessageVisible);
    }

    private void updateView() {
        double currentQuestionCount = totalQuestionCount - linkedListQuestions.size();
        double progressValue = currentQuestionCount / totalQuestionCount;
        int percent = (int) (progressValue * 100);
        pgbarLessonProgress.setProgress(progressValue);
        labelPercent.setText(String.valueOf(percent) + '%');

    }

    // TODO: Reset answer of question
}
