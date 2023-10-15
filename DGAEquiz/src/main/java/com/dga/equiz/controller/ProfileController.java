package com.dga.equiz.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Circle circle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image(String.valueOf(getClass().getResource("/image/test.jpg")));
        circle.setFill(new ImagePattern(img));
    }
}
