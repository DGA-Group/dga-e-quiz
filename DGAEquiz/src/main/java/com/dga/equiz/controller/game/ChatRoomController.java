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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
    public Button btnReturn;

    @FXML
    public Label lbPlayerCount;

    @FXML
    public ScrollPane spChatScroll;

    @FXML
    public ChoiceBox<String> cbGameMode;

    private Map<String, Image> userAvatars = new HashMap<>();

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
        btnReturn.setOnAction(event -> onClickReturn());
        cbGameMode.getItems().add("Red Tea");
        cbGameMode.getItems().add("Blue Tea");
        cbGameMode.setValue("Mode");
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

    public Map<String, Image> getUserAvatars() {
        return userAvatars;
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
                MessageAlignment alignment = MessageAlignment.TopLeft;
                Profile profile = ApplicationData.getInstance().profile;
                int userId = profile.getID();
                if (message.name.equals("Server")) {
                    alignment = MessageAlignment.Middle;
                } else if (message.userId == userId) {
                    alignment = MessageAlignment.TopRight;
                }
                controller.setUpMessageBox(message.username, message.name, message.text, alignment);
            } catch (IOException ignore) {
            }
        });
    }

    public void addMyMessage(String message, MessageAlignment alignment) {
        Platform.runLater(() -> {
            try {
                NodeObject nodeObject = EquizUtils.Instantiate("/view/game/MessageBoxView.fxml", vboxMessageList);
                MessageBoxController controller = nodeObject.getController();
                Profile profile = ApplicationData.getInstance().profile;
                String username = profile.getUsername();
                String name = profile.getName();
                controller.setUpMessageBox(username, name, message, alignment);
            } catch (IOException ignore) {
            }
        });
    }

    public void onClickStartGame() {
        String gameMode;
        switch (cbGameMode.getValue()) {
            case "Red Tea":
                gameMode = "red_tea";
                break;
            case "Blue Tea":
                gameMode = "blue_tea";
                break;
            case "Green Tea":
                gameMode = "green_tea";
                break;
            default:
                EquizUtils.showAlert("Error", null, "Please choose a game mode!", AlertType.ERROR);
                return;
        }
        ClientHelperRequest.sendStartGameRequest(gameMode);
    }

    public void onClickReturn() {
        GameController gameController = ControllerManager.getInstance().gameController;
        gameController.chatRoomView.hide();
        gameController.lobbyView.show();
        ClientHelperRequest.sendLeaveRoomRequest();
    }

    public void addPlayer(String username) {
        if (!userAvatars.containsKey(username)) {
            Image playerAva = new Image(getClass().getResourceAsStream("/image/test.jpg"));
            try {
                playerAva = EquizUtils.toImage(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
            userAvatars.put(username, playerAva);
        }
    }

    public void removePlayer(String username) {
        userAvatars.remove(username);
    }


}
