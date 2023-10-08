package com.dga.equiz.controller.learn;

import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.EquizUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LearnController implements Initializable {

    //region Singleton
    private static LearnController instance;

    public static LearnController getInstance() {
        return instance;
    }
    //endregion

    //region FXML Reference
    @FXML
    private ProgressBar pgbarLessonProgress;

    @FXML
    private Button buttonClose;

    @FXML
    private AnchorPane panelQuestion;

    @FXML
    private Label labelComment;

    @FXML
    private Button buttonSubmit;

    @FXML
    private Button buttonContinue;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        System.gc();

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        NodeObject testQuestion = EquizUtils.Instantiate("/view/question/ImageQuestionView.fxml");
        panelQuestion.getChildren().add(testQuestion.getNode());
        testQuestion.show();

        NodeObject testQuestion2 = EquizUtils.Instantiate("/view/question/ListeningQuestionView.fxml");
        panelQuestion.getChildren().add(testQuestion2.getNode());
        testQuestion2.show();

        NodeObject testQuestion3 = EquizUtils.Instantiate("/view/question/FillQuestionView.fxml");
        panelQuestion.getChildren().add(testQuestion3.getNode());
        testQuestion3.show();

        NodeObject testQuestion4 = EquizUtils.Instantiate("/view/question/TranslateQuestionView.fxml");
        panelQuestion.getChildren().add(testQuestion4.getNode());
        testQuestion4.show();
    }
}
