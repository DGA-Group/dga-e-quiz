package com.dga.equiz.controller.game;

import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.ControllerManager;
import com.dga.equiz.utils.EquizUtils;
import com.dga.game.ClientHelperRequest;
import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.Message.MessageResponse;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    }

    public void onClickSendMessage() {
        String message = tfChatInputBox.getText();
        ClientHelperRequest.sendMessageRequest(message);
    }

    public void clearMessageList() {
        Platform.runLater(() -> {
            vboxMessageList.getChildren().clear();
        });
    }

    public void addMessage(MessageResponse message) {
        Platform.runLater(() -> {
            try {
                NodeObject nodeObject = EquizUtils.Instantiate("/view/game/MessageBoxView.fxml", vboxMessageList);
                MessageBoxController controller = nodeObject.getController();
                controller.setUpMessageBox(message.text);
            } catch (IOException ignore) {
            }
        });
    }

    public void onClickStartGame() {
        ClientHelperRequest.sendStartGameRequest();
    }
}
