package com.dga.equiz.controller;

import com.dga.equiz.utils.DBHelper;
import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.utils.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void onClickAdd() throws IOException, SQLException {
        String word = wordText.getText();
        String pronounce = taPronounce.getText();
        String description = taDescription.getText();
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        if (word.equals("") && pronounce.equals("") && description.equals("")) {
            EquizUtils.showAlert("Please add word !!!");
        }
        String checkQuery = "SELECT  word, pronounce, description FROM av WHERE word = '" + word
                + "' AND pronounce = '" + pronounce + "' AND description = '" + description + "';";
        resultSet = DBHelper.executeQuerySqlite(checkQuery);
        statement = resultSet.getStatement();
        connection = statement.getConnection();
        if (resultSet.next()) {
            EquizUtils.showAlert("Already Exist !");
        } else {
            String query = "INSERT INTO av(word, pronounce, description) VALUES " +
                    "('" + word + "','" + pronounce + "','" + description + "');";
            try {
                DBHelper.executeUpdateSqlite(query);
            } catch (Exception e) {
                e.printStackTrace();
            }
            StageManager.getInstance().offlineAddDictionaryStage.hide();
            EquizUtils.showAlert("Your word has been added." + "\n" + "Please research it.");
        }
        DBHelper.closeQuery(resultSet, statement, connection);
    }

}
