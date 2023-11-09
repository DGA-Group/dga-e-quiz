package com.dga.equiz.controller.editProfile;

import com.dga.equiz.model.Profile;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Circle circle;
    @FXML
    public Button buttonEdit;

    @FXML
    public Label labelName;

    @FXML
    public Label labelMail;

    @FXML
    public Label labelDOB;

    @FXML
    public Label labelPhone;

    @FXML
    public Label labelGithub;

    @FXML
    public Label labelID;

    @FXML
    public Label labelLevel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAvatar("test.jpg");
        try {
            setLabel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAvatar(String path) {
        Image img = new Image(String.valueOf(getClass().getResource("/image/" + path)));
        circle.setFill(new ImagePattern(img));
    }

    public void changeImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            Image newImage = new Image(file.toURI().toString());
            circle.setFill(new ImagePattern(newImage));
        }
    }

    public void setLabel() throws SQLException {
        String sqlQuery = "SELECT * FROM `information` WHERE id = 1";
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;

        resultSet = DBHelper.executeQuery(sqlQuery);
        statement = resultSet.getStatement();
        connection = statement.getConnection();

        resultSet.next();

        Profile profile = ApplicationData.getInstance().profile;

        profile.setID(resultSet.getInt(1));
        profile.setName(resultSet.getString(2));
        profile.setMail(resultSet.getString(3));
        profile.setDob(resultSet.getString(4));
        profile.setPhone(resultSet.getString(5));
        profile.setGithub(resultSet.getString(6));
        profile.setLevel(resultSet.getString(8));



        labelName.setText(profile.getName());
        labelMail.setText(profile.getMail());
        labelDOB.setText(profile.getDob());
        labelPhone.setText(profile.getPhone());
        labelGithub.setText(profile.getGithub());
        labelID.setText(String.valueOf(profile.getID()));
        labelLevel.setText(profile.getLevel());

        DBHelper.closeQuery(resultSet,statement,connection);
    }
}
