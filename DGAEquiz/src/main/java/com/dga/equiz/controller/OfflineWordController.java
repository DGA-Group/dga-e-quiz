package com.dga.equiz.controller;

import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.ApplicationData;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.SimpleTimeZone;

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
    @FXML
    private Button btnSave;
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
    public void onClickSave() throws SQLException {
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        String savedWord = labelOfWord.getText();
        String savedDescription = labelOfDescription.getText();
        int userID = ApplicationData.getInstance().profile.getID();
        String query = "SELECT id , word, description FROM ALM WHERE id = '" + userID
                + "' AND word = '" + savedWord + "' AND description = '" + savedDescription + "';";
        resultSet = DBHelper.executeQuery(query);
        statement = resultSet.getStatement();
        connection = statement.getConnection();
        while (resultSet.next()) {
            if (resultSet.next()){
                EquizUtils.showAlert("Already Exist !");
            }
            else {
                String updateQuery = "INSERT INTO ALM (id, word, description) VALUES ('"
                        + userID + "','" + savedWord + "','" + savedDescription + "');";
                try {
                    DBHelper.executeUpdateSqlite(query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    DBHelper.closeQuery(resultSet, statement, connection);
                }
            }
        }
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
