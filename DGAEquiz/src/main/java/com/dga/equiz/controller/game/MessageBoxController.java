package com.dga.equiz.controller.game;

import com.dga.equiz.utils.ApplicationEnum.MessageAlignment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageBoxController implements Initializable {

    @FXML
    public HBox hBoxContainer;

    @FXML
    public Label lbMessageContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUpMessageBox(String content, MessageAlignment alignment) {
        lbMessageContent.setText(content);
        switch (alignment){
            case Middle:
               lbMessageContent.setAlignment(Pos.TOP_CENTER);
                hBoxContainer.setAlignment(Pos.TOP_CENTER);
                break;
            case TopLeft:
                lbMessageContent.setAlignment(Pos.TOP_LEFT);
                hBoxContainer.setAlignment(Pos.TOP_LEFT);
                break;
            case TopRight:
                lbMessageContent.setAlignment(Pos.TOP_RIGHT);
                hBoxContainer.setAlignment(Pos.TOP_RIGHT);
                break;
        }
    }
}
