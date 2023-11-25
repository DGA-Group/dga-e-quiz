package com.dga.equiz.controller;

import com.dga.equiz.utils.DBHelper;
import com.dga.equiz.utils.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OfflineChangeWordController {
    @FXML
    private Label labelWord;
    @FXML
    private TextArea textAreaPronounce;
    @FXML
    private TextArea textAreaDescription;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnCheck;
    private OfflineDictionaryController dictionaryController;
    public void onClickClose() {
        StageManager.getInstance().changeWordStage.hide();
    }

    public void setupChangeWordView(String changedWord, String changedPronounce, String changedDescription, OfflineDictionaryController offlineDictionaryController) {
        labelWord.setText(changedWord);
        textAreaPronounce.setText(changedPronounce);
        textAreaDescription.setText(changedDescription);
        dictionaryController = offlineDictionaryController;
    }

    public void onClickConfirm() throws IOException {
        String updatedPronounce = textAreaPronounce.getText();
        String updatedDescription = textAreaDescription.getText();
        String query = "UPDATE av SET pronounce = '" + updatedPronounce
                + "', description = '" + updatedDescription
                + "' WHERE word = '" + labelWord.getText() + "'";
        try {
            DBHelper.executeUpdateSqlite(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StageManager.getInstance().changeWordStage.hide();
        dictionaryController.onClickSearch();
    }

}
