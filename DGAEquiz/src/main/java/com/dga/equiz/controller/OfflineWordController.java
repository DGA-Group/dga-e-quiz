package com.dga.equiz.controller;

import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.DBHelper;
import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.utils.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OfflineWordController implements Initializable {
    @FXML
    private Label labelOfWord;
    @FXML
    private Label labelOfPronounce;
    @FXML
    private Label labelOfDescription;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnDelete;
    private ChangeWordController changeWordController;
    private OfflineDictionaryController dictionaryController;
    public void setupWordView(String word, String pronounce, String description, OfflineDictionaryController offlineDictionaryController) {
        this.labelOfWord.setText(word);
        this.labelOfDescription.setText(description);
        this.labelOfPronounce.setText(pronounce);
        this.dictionaryController = offlineDictionaryController;
        labelOfWord.setWrapText(true);
        labelOfPronounce.setWrapText(true);
        labelOfDescription.setWrapText(true);
    }
    public void onClickChangeWord() throws IOException {
        String changedWord = labelOfWord.getText();
        String changedPronounce = labelOfPronounce.getText();
        String changedDescription = labelOfDescription.getText();
        changeWordController.setupChangeWordView(changedWord,changedPronounce,changedDescription);
        StageManager.getInstance().changeWordStage.show();
        dictionaryController.onClickSearch();
    }

    public void onClickDelete() throws IOException {
        String deletedWord = labelOfWord.getText();
        String deletedDescription = labelOfDescription.getText();
        String deletedPronounce = labelOfPronounce.getText();
        String query = "DELETE FROM av WHERE word = '" + deletedWord + "' AND pronounce = '"
                + deletedPronounce + "' AND description = '" + deletedDescription + "';";
        try {
            DBHelper.executeUpdateSqlite(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dictionaryController.onClickSearch();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            NodeObject changeWordView = EquizUtils.Instantiate("/view/ChangeWordView.fxml");
            this.changeWordController = changeWordView.getController();
            Scene changeWordScene = new Scene((Parent) changeWordView.getNode());
            Stage changeWordViewStage = StageManager.getInstance().changeWordStage = new Stage();
            changeWordViewStage.initStyle(StageStyle.TRANSPARENT);
            changeWordViewStage.setScene(changeWordScene);
            changeWordViewStage.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
