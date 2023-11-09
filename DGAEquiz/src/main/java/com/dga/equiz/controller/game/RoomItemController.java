package com.dga.equiz.controller.game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomItemController implements Initializable {
    @FXML
    public Label lbRoomId;

    @FXML
    public Label lbRoomName;

    @FXML
    public Label lbNumberOfPlayer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setupRoomItem(String roomId, String rooName, String numberOfPlayer) {
        lbRoomId.setText(roomId);
        lbRoomName.setText(rooName);
        lbNumberOfPlayer.setText(numberOfPlayer);
    }
}
