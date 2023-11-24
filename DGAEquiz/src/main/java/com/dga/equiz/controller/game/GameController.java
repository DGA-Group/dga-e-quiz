package com.dga.equiz.controller.game;

import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.ApplicationEnum.AnchorType;
import com.dga.equiz.utils.ControllerManager;
import com.dga.equiz.utils.EquizUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private AnchorPane paneGame;

    public NodeObject lobbyView = null;
    public NodeObject chatRoomView = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManager.getInstance().gameController = this;
        setupLobby();
    }

    private void setupLobby() {
        try {
            lobbyView = EquizUtils.Instantiate("/view/game/LobbyView.fxml", paneGame, AnchorType.FitToParent);
            lobbyView.show();

            chatRoomView = EquizUtils.Instantiate("/view/game/ChatRoomView.fxml", paneGame);
            chatRoomView.hide();
        } catch (Exception ignore) {
        }
    }

}
