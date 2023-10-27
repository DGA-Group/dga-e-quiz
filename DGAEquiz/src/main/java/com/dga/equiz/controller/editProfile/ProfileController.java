package com.dga.equiz.controller.editProfile;

import com.dga.equiz.controller.editProfile.EditInforController;
import com.dga.equiz.model.Profile;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.DBHelper;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public static Profile profile = new Profile();

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
