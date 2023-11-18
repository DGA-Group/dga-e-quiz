package com.dga.equiz.controller.login;

import com.dga.equiz.utils.DBHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RankController implements Initializable {
    @FXML
    private Label labelRank_name1;

    @FXML
    private Label labelRank_name2;

    @FXML
    private Label labelRank_name3;

    @FXML
    private Label labelRank_name4;

    @FXML
    private Label labelRank_name5;

    @FXML
    private Label labelRank_score1;

    @FXML
    private Label labelRank_score2;

    @FXML
    private Label labelRank_score3;

    @FXML
    private Label labelRank_score4;

    @FXML
    private Label labelRank_score5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String query = "SELECT username, score FROM information ORDER BY score DESC LIMIT 5";
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;

        try {
            resultSet = DBHelper.executeQuery(query);
            statement = resultSet.getStatement();
            connection = statement.getConnection();

            for (int i = 1; i <= 5 && resultSet.next(); i++) {
                if (resultSet.getString("username") != null && resultSet.getInt("score") != 0) {
                    setRank(getUsernameLabel(i), getScoreLabel(i), resultSet.getString("username"), resultSet.getString("score"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Label getUsernameLabel(int rank) {
        switch (rank) {
            case 1:
                return labelRank_name1;
            case 2:
                return labelRank_name2;
            case 3:
                return labelRank_name3;
            case 4:
                return labelRank_name4;
            case 5:
                return labelRank_name5;
            default:
                throw new IllegalArgumentException("Invalid rank: " + rank);
        }
    }

    public Label getScoreLabel(int rank) {
        switch (rank) {
            case 1:
                return labelRank_score1;
            case 2:
                return labelRank_score2;
            case 3:
                return labelRank_score3;
            case 4:
                return labelRank_score4;
            case 5:
                return labelRank_score5;
            default:
                throw new IllegalArgumentException("Invalid rank: " + rank);
        }
    }

    public void setRank(Label labelUsername, Label labelScore, String username, String score) {
        labelUsername.setText(username);
        labelScore.setText(score);
    }

}
