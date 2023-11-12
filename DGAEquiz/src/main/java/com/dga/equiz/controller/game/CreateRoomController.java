package com.dga.equiz.controller.game;

import com.dga.equiz.utils.ControllerManager;
import com.dga.equiz.utils.EquizUtils;
import com.dga.game.ClientHelperRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    public TextField tfRoomPassword;

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
            String roomPassword = tfRoomPassword.getText();
            int playerLimit = Integer.parseInt(tfPlayerLimit.getText());
            ClientHelperRequest.sendCreateRoomRequest(roomName, roomPassword, playerLimit);
        });
    }


}
