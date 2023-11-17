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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OfflineDictionaryController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField searchingField;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonSearch;
    @FXML
    private VBox wordsBox;
    @FXML
    private ScrollPane Scrollpane;
    private ArrayList<String> suggestions = new ArrayList<>();

    public OfflineDictionaryController() {
    }

    public void onStartup() {
        TextFields.bindAutoCompletion(searchingField, suggestions);

        searchingField.textProperty().addListener((obs, oldText, newText) -> {
            suggestions.clear();
            updatedSuggestions(newText);
            TextFields.bindAutoCompletion(searchingField, suggestions);
        });
    }

    public void updatedSuggestions(String word) {
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {
            String query = "SELECT * FROM av WHERE word LIKE '%" + word + "%'";
            resultSet = DBHelper.executeQuerySqlite(query);
            statement = resultSet.getStatement();
            connection = statement.getConnection();
            while (resultSet.next()) {
                suggestions.add(resultSet.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBHelper.closeQuery(resultSet, statement, connection);
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onStartup();
        try {
            NodeObject offlineAddDictionaryView = EquizUtils.Instantiate("/view/OfflineAddWordView.fxml");
            Scene offlineAddDictionaryScene = new Scene((Parent) offlineAddDictionaryView.getNode());
            Stage offlineAddDictionaryViewStage = StageManager.getInstance().offlineAddDictionaryStage = new Stage();
            offlineAddDictionaryViewStage.initStyle(StageStyle.TRANSPARENT);
            offlineAddDictionaryViewStage.setScene(offlineAddDictionaryScene);
            offlineAddDictionaryViewStage.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickAddWord() throws IOException {
        StageManager.getInstance().offlineAddDictionaryStage.show();
        onClickSearch();
    }

    public void onClickSearch() throws IOException {
        String inp = searchingField.getText().trim();
        wordsBox.getChildren().clear();
        displayWord(inp);
    }

    private void displayWord(String input) throws IOException {
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {
            String query = "SELECT * FROM av WHERE word = '" + input + "'";
            resultSet = DBHelper.executeQuerySqlite(query);
            statement = resultSet.getStatement();
            connection = statement.getConnection();
            String word = "";
            String description = "";
            String pronounce = "";
            while (resultSet.next()) {
                NodeObject wordView = EquizUtils.Instantiate("/view/OfflineWordView.fxml");
                wordsBox.getChildren().add(wordView.getNode());
                OfflineWordController controller = wordView.getController();
                word = resultSet.getString(2);
                description = resultSet.getString(4);
                pronounce = resultSet.getString(5);
                controller.setupWordView(word, pronounce, description, this);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBHelper.closeQuery(resultSet, statement, connection);
            } catch (Exception ignore) {
            }
        }
    }
}
