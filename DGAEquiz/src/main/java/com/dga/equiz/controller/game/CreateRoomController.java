package com.dga.equiz.controller.game;

import com.dga.equiz.utils.ControllerManager;
import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.utils.StageManager;
import com.dga.game.ClientHelperRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateRoomController implements Initializable {
    @FXML
    public Button btnClose;

    @FXML
    private AnchorPane paneParent;

    @FXML
    public Button btnCreate;

    @FXML
    public TextField tfRoomName;

    @FXML
    public TextField tfPlayerLimit;


    private Stage stage;

    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManager.getInstance().createRoomController = this;
        EquizUtils.callFuncDelay(this::setupCreateRoom, 1000);
    }

    public void setupCreateRoom() {
        stage = (Stage) this.btnClose.getScene().getWindow();
        this.btnClose.setOnAction((ActionEvent event) -> {
            stage.hide();
        });

        this.paneParent.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });

        this.paneParent.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });

        this.btnCreate.setOnAction(event -> {
            String roomName = tfRoomName.getText();
            String roomPassword = "";
            int playerLimit;
            try {
                playerLimit = Integer.parseInt(tfPlayerLimit.getText());
            } catch (Exception e) {
                EquizUtils.showAlert("Error", null,
                        "Do not input player limit as character!", AlertType.ERROR);
                return;
            }

            tfRoomName.clear();
            tfPlayerLimit.clear();
            (btnCreate.getScene().getWindow()).hide();

            ControllerManager.getInstance().chatRoomController.vboxMessageList.getChildren().clear();
            ClientHelperRequest.sendCreateRoomRequest(roomName, false, roomPassword, playerLimit);
        });
    }


}
