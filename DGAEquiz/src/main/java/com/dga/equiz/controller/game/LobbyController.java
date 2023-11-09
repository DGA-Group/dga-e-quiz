package com.dga.equiz.controller.game;

import com.dga.equiz.model.game.RoomItem;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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

    public Stage createRoomWindow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupButton();
        setupWindow();
    }

    private void setupButton() {
        btnCreateRoom.setOnAction((ActionEvent event) -> {
            onClickCreateRoom();
        });
    }

    private void setupWindow() {
        try {
            setupCreateRoomWindow();
        } catch (Exception e) {
            System.out.println("Unable to create window Create Room!");
        }
    }

    private void setupCreateRoomWindow() throws IOException {
        createRoomWindow = new Stage();
        createRoomWindow.initStyle(StageStyle.TRANSPARENT);

        NodeObject nodeObject = EquizUtils.Instantiate("/view/game/CreateRoomView.fxml");
        Scene scene = new Scene((Parent) nodeObject.getNode());
        createRoomWindow.setScene(scene);
        CreateRoomController controller = nodeObject.getController();
        controller.setupCreateRoom();
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

    public void onClickCreateRoom() {
        createRoomWindow.show();
    }


}
