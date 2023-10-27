package com.dga.equiz.controller;

import com.dga.equiz.controller.editProfile.EditAccController;
import com.dga.equiz.controller.editProfile.EditInforController;
import com.dga.equiz.controller.editProfile.EditSelectController;
import com.dga.equiz.controller.editProfile.ProfileController;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileContainerController implements Initializable {
    @FXML
    private Pane paneDefault;
    private NodeObject editInforView;

    private NodeObject editSelectView;

    private NodeObject editAccView;

    private NodeObject profileView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            addPane();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        initButton();
    }

    // add 4 pane to container.
    public void addPane() throws IOException {
        profileView = EquizUtils.Instantiate("/view/editProfile/ProfileView.fxml");
        paneDefault.getChildren().add(profileView.getNode());
        profileView.show();
        editSelectView = EquizUtils.Instantiate("/view/editProfile/EditSelectView.fxml");
        paneDefault.getChildren().add(editSelectView.getNode());
        editSelectView.hide();
        editInforView = EquizUtils.Instantiate("/view/editProfile/EditInforView.fxml");
        paneDefault.getChildren().add(editInforView.getNode());
        editInforView.hide();
        editAccView = EquizUtils.Instantiate("/view/editProfile/EditAccView.fxml");
        paneDefault.getChildren().add(editAccView.getNode());
        editAccView.hide();
    }

    // add change function to a specific button.
    public void changeButton(Button button, NodeObject layout1, NodeObject layout2) {
        button.setOnAction((ActionEvent event) -> {
            layout1.hide();
            layout2.show();
        });
    }

    // initialize all function of buttons in each Pane.
    public void initButton() {
        ProfileController controllerProfile = profileView.getController();
        Button buttonEdit = controllerProfile.buttonEdit;
        changeButton(buttonEdit, profileView, editSelectView);

        EditSelectController controllerSelect = editSelectView.getController();
        Button buttonBack1 = controllerSelect.buttonBack;
        Button buttonInfor = controllerSelect.buttonInfor;
        Button buttonPassword = controllerSelect.buttonPassword;
        changeButton(buttonBack1, editSelectView, profileView);
        changeButton(buttonInfor, editSelectView, editInforView);
        changeButton(buttonPassword, editSelectView, editAccView);

        EditInforController controllerInfor = editInforView.getController();
        Button buttonBack2 = controllerInfor.buttonBack;
        changeButton(buttonBack2, editInforView, editSelectView);

        EditAccController controllerAcc = editAccView.getController();
        Button buttonBack3 = controllerAcc.buttonBack;
        changeButton(buttonBack3, editAccView, editSelectView);
    }
}
