package com.dga.equiz.controller.game;

import com.dga.equiz.utils.ApplicationEnum.MessageAlignment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageBoxController implements Initializable {

    @FXML
    public HBox hBoxContainer;

    @FXML
    public VBox vboxContainer;

    @FXML
    public Label lbMessageContent;

    @FXML
    public Label lbName;

    @FXML
    public Circle ccUserImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUpMessageBox(String name, String content, MessageAlignment alignment) {
        lbMessageContent.setText(content);
        lbName.setText(name);
        switch (alignment) {
            case Middle:
                lbMessageContent.setAlignment(Pos.TOP_CENTER);
                hBoxContainer.setAlignment(Pos.TOP_CENTER);
                vboxContainer.setAlignment(Pos.TOP_CENTER);
                vboxContainer.getChildren().remove(lbName);
                hBoxContainer.getChildren().remove(ccUserImage);
                break;
            case TopLeft:
                hBoxContainer.nodeOrientationProperty().setValue(NodeOrientation.LEFT_TO_RIGHT);
                break;
            case TopRight:
                hBoxContainer.nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
                break;
        }
    }
}
