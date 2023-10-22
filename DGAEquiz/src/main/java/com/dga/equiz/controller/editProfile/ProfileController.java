package com.dga.equiz.controller.editProfile;

import com.dga.equiz.controller.editProfile.EditInforController;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Circle circle;
    @FXML
    public Button buttonEdit;

    //    @FXML
//    private DatePicker myDatePicker;
//
//    @FXML
//    private Label birthdayLabel;
//
//    @FXML
//    private Label mailLabel;
//
//    @FXML
//    private TextField mailTextField;


//    public void getMail(ActionEvent event) {
//            String text = mailTextField.getText();
//            mailLabel.setText(text);
//            mailTextField.setVisible(false);
//    }

//    public void getDate(ActionEvent event) throws SQLException {
//        LocalDate myDate = myDatePicker.getValue();
////        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//        String sqlTestQuery = "SELECT * FROM account";
//        ResultSet resultSet = DBHelper.query(sqlTestQuery);
//        resultSet.next();
//        String myFormattedDate = String.valueOf(resultSet.getDate(4));
//        birthdayLabel.setText(myFormattedDate);
//        myDatePicker.setVisible(false);
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAvatar("test.jpg");
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
}
