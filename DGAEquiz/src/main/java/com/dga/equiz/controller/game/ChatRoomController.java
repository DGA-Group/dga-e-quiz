package com.dga.equiz.controller.game;

import com.dga.equiz.model.Profile;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.ApplicationEnum.MessageAlignment;
import com.dga.equiz.utils.ControllerManager;
import com.dga.equiz.utils.EquizUtils;
import com.dga.game.ClientHelperRequest;
import com.dga.game.EquizPacket.Message.MessageResponse;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatRoomController implements Initializable {
    @FXML
    public TextField tfChatInputBox;

    @FXML
    public Button btnSendMessage;

    @FXML
    public VBox vboxMessageList;

    @FXML
    public Button btnStartGame;

    @FXML
    public Label lbPlayerCount;

    @FXML
    public ScrollPane spChatScroll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManager.getInstance().chatRoomController = this;
        tfChatInputBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onClickSendMessage();
            }
        });

        btnSendMessage.setOnAction(event -> onClickSendMessage());
        btnStartGame.setOnAction(event -> onClickStartGame());
        spChatScroll.vvalueProperty().bind(vboxMessageList.heightProperty());
    }

    public void onClickSendMessage() {
        String message = tfChatInputBox.getText();
        if (message.equals("")) {
            return;
        }
        addMyMessage(message, MessageAlignment.TopRight);
        ClientHelperRequest.sendMessageRequest(message);
        tfChatInputBox.clear();
    }

    public void clearMessageList() {
        Platform.runLater(() -> {
            vboxMessageList.getChildren().clear();
        });
    }

    public void updatePlayerCount(String message) {
        Platform.runLater(() -> {
            lbPlayerCount.setText(message);
        });
    }

    public void addMessage(MessageResponse message) {
        Platform.runLater(() -> {
            try {
                NodeObject nodeObject = EquizUtils.Instantiate("/view/game/MessageBoxView.fxml", vboxMessageList);
                MessageBoxController controller = nodeObject.getController();

                if (message.userId.equals("Server")) {
                    controller.setUpMessageBox(message.text, MessageAlignment.Middle);
                } else {
                    controller.setUpMessageBox(message.text, MessageAlignment.TopLeft);
                }

            } catch (IOException ignore) {
            }
        });
    }

    public void addMyMessage(String message, MessageAlignment alignment) {
        Platform.runLater(() -> {
            try {
                NodeObject nodeObject = EquizUtils.Instantiate("/view/game/MessageBoxView.fxml", vboxMessageList);
                MessageBoxController controller = nodeObject.getController();
                controller.setUpMessageBox(message, alignment);
            } catch (IOException ignore) {
            }
        });
    }

    public void onClickStartGame() {
        ClientHelperRequest.sendStartGameRequest();
    }
}
