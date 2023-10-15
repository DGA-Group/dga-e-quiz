package com.dga.equiz.controller;

import com.dga.equiz.controller.question.ImageQuestionController;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
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
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
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

    }
}
