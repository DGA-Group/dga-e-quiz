package com.dga.equiz.controller;

import com.dga.equiz.utils.DBHelper;
import com.dga.equiz.utils.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ChangeWordController {
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

    public void onClickClose() {
        StageManager.getInstance().changeWordStage.hide();
    }

    public void setupChangeWordView(String changedWord, String changedPronounce, String changedDescription) {
        labelWord.setText(changedWord);
        textAreaPronounce.setText(changedPronounce);
        textAreaDescription.setText(changedDescription);
    }

    public void onClickConfirm() {
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

    }

}
