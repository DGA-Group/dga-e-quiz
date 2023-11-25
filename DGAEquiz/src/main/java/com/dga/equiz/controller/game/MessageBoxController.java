package com.dga.equiz.controller.game;

import com.dga.equiz.model.Profile;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.ApplicationEnum.MessageAlignment;
import com.dga.equiz.utils.ControllerManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Map;
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

    @FXML
    public HBox hBoxAlignment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUpMessageBox(String username, String name, String content, MessageAlignment alignment) {
        Profile profile = ApplicationData.getInstance().profile;
        ChatRoomController controller = ControllerManager.getInstance().chatRoomController;
        lbMessageContent.setText(content);
        lbName.setText(name);
        Map<String, Image> avatar = controller.getUserAvatars();
        Image userAva = avatar.get(username);
        switch (alignment) {
            case Middle:
                lbMessageContent.setAlignment(Pos.TOP_CENTER);
                hBoxContainer.setAlignment(Pos.TOP_CENTER);
                hBoxAlignment.setAlignment(Pos.TOP_CENTER);
                vboxContainer.setAlignment(Pos.TOP_CENTER);
                vboxContainer.getChildren().remove(lbName);
                hBoxContainer.getChildren().remove(ccUserImage);
                break;
            case TopLeft:
                hBoxContainer.nodeOrientationProperty().setValue(NodeOrientation.LEFT_TO_RIGHT);
                hBoxAlignment.setAlignment(Pos.TOP_LEFT);
                ccUserImage.setFill(new ImagePattern(userAva));
                break;
            case TopRight:
                hBoxContainer.nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
                hBoxAlignment.setAlignment(Pos.TOP_RIGHT);
                ccUserImage.setFill(new ImagePattern(userAva));
                break;
        }
    }
}
