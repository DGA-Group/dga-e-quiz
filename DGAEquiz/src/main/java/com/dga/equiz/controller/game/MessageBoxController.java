package com.dga.equiz.controller.game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageBoxController implements Initializable {

    @FXML
    public Label lbMessageContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUpMessageBox(String content) {
        lbMessageContent.setText(content);
    }
}
