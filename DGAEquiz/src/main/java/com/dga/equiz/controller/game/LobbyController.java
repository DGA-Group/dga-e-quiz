package com.dga.equiz.controller.game;

import com.dga.equiz.model.game.RoomItem;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.EquizUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyController implements Initializable {
    @FXML
    public Label lbRoomList;

    @FXML
    public Label lbRoomId;

    @FXML
    public Label lbRoomName;

    @FXML
    public Label lbNumberOfPlayer;

    @FXML
    public Button btnRefresh;

    @FXML
    public VBox vboxRoomList;

    @FXML
    public Button btnCreateRoom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void clearRoomList() {
        vboxRoomList.getChildren().clear();
    }

    public void addRoomToList(RoomItem roomItem) {
        try {
            NodeObject room = EquizUtils.Instantiate("/view/game/RoomItemView.fxml", vboxRoomList);
            RoomItemController controller = room.getController();
            String roomId = roomItem.getRoomId();
            String roomName = roomItem.getRoomName();
            String roomPlayer = "" + roomItem.getCurrentPlayer() + '/' + roomItem.getPlayerLimit();
            controller.setupRoomItem(roomId, roomName, roomPlayer);
        } catch (Exception e) {
            System.out.println("Unable to add room to list!");
        }

    }

    public void createRoom() {

    }


}
