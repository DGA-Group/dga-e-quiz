package com.dga.equiz.controller.game;

import com.dga.equiz.utils.ControllerManager;
import com.dga.game.ClientHelperRequest;
import com.dga.game.EquizPacket.Room.ShowRoom.RoomWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @FXML
    public Button btnJoinRoom;

    private RoomWrapper room;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setupRoomItem(String roomId, String rooName, String numberOfPlayer) {
        lbRoomId.setText(roomId);
        lbRoomName.setText(rooName);
        lbNumberOfPlayer.setText(numberOfPlayer);
    }

    public void setRoom(RoomWrapper room) {
        this.room = room;
    }

    public void onClickJoinRoom() {
        ClientHelperRequest.sendJoinRoomRequest(room.roomId, room.roomPassword);
        ChatRoomController chatRoomController = ControllerManager.getInstance().chatRoomController;
        chatRoomController.clearMessageList();
    }
}
