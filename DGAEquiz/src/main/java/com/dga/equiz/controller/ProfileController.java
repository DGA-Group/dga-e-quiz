package com.dga.equiz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Circle circle;

    @FXML
    private DatePicker myDatePicker;

    @FXML
    private Label birthdayLabel;

    @FXML
    private Label mailLabel;

    @FXML
    private TextField mailTextField;

    @FXML
    private Button editButton;

    @FXML
    private Label nameLabel;

    public void getMail(ActionEvent event) {
            String text = mailTextField.getText();
            mailLabel.setText(text);
            mailTextField.setVisible(false);
    }

    public void getDate(ActionEvent event) {
        LocalDate myDate = myDatePicker.getValue();
        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        birthdayLabel.setText(myFormattedDate);
        myDatePicker.setVisible(false);
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

//    public void editProfile(ActionEvent event) {
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image(String.valueOf(getClass().getResource("/image/test.jpg")));
        circle.setFill(new ImagePattern(img));
    }
}
