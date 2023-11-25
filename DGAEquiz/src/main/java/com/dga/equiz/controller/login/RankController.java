package com.dga.equiz.controller.login;

import com.dga.equiz.utils.ControllerManager;
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
        ControllerManager.getInstance().rankController = this;
        reloadRank();
    }

    public void reloadRank() {
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
        } finally {
            try {
                DBHelper.closeQuery(resultSet, statement, connection);
            } catch (Exception ignore) {
            }
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

    public int findUserRank(int id) {
        String sql = "SELECT RANK() OVER (ORDER BY score) AS `rank` FROM information WHERE id = '" + id + "';";
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {
            resultSet = DBHelper.executeQuery(sql);
            statement = resultSet.getStatement();
            connection = statement.getConnection();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBHelper.closeQuery(resultSet, statement, connection);
            } catch (Exception ignore) {
            }
        }
        return 10000;
    }

    public int findUserPoint(int id) {
        String sql = "select score from information where id = '" + id + "';";
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {
            resultSet = DBHelper.executeQuery(sql);
            statement = resultSet.getStatement();
            connection = statement.getConnection();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBHelper.closeQuery(resultSet, statement, connection);
            } catch (Exception ignore) {
            }
        }
        return 0;
    }

}
