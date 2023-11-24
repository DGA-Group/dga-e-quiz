package com.dga.equiz.controller;

import com.dga.equiz.utils.DBHelper;
import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.utils.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class OfflineAddWordController {
    @FXML
    private VBox vBox;
    @FXML
    private TextField wordText;
    @FXML
    private TextArea taPronounce;
    @FXML
    private TextArea taDescription;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnConfirm;

    public void onClickClose() {
        StageManager.getInstance().offlineAddDictionaryStage.hide();
    }

    public void onClickAdd() {
        String word = wordText.getText();
        String pronounce = taPronounce.getText();
        String description = taDescription.getText();
        if (word.equals("") && pronounce.equals("") && description.equals("")){
            EquizUtils.showAlert("Please add word !!!");
        }
        String query = "INSERT INTO av(word, description, pronounce) VALUES " +
                "('" + word + "','" + pronounce + "','" + description + "');";
        try {
            DBHelper.executeUpdateSqlite(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
