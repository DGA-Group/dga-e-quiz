package com.dga.equiz.controller.learn;

import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        NodeObject testQuestion = EquizUtils.Instantiate("/view/question/ImageQuestionView.fxml");
        panelQuestion.getChildren().add(testQuestion.getNode());
        testQuestion.hide();

        NodeObject testQuestion2 = EquizUtils.Instantiate("/view/question/ListeningQuestionView.fxml");
        panelQuestion.getChildren().add(testQuestion2.getNode());
        testQuestion2.hide();

        NodeObject testQuestion3 = EquizUtils.Instantiate("/view/question/FillQuestionView.fxml");
        panelQuestion.getChildren().add(testQuestion3.getNode());
        testQuestion3.show();

        NodeObject testQuestion4 = EquizUtils.Instantiate("/view/question/TranslateQuestionView.fxml");
        panelQuestion.getChildren().add(testQuestion4.getNode());
        testQuestion4.hide();
    }
}
