package com.dga.equiz.controller;

import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.ApplicationEnum.AnchorType;
import com.dga.equiz.utils.ControllerManager;
import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.utils.StageManager;
import com.dga.game.ClientHelperRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MyApplicationController implements Initializable {

    //region FXML Reference
    @FXML
    private AnchorPane panelHolder;

    @FXML
    private Button btnClose;

    @FXML
    private Button logOutButton;

    @FXML
    private HBox hboxUpperBar;
    //endregion

    private NodeObject homeView = null;
    private NodeObject dictionaryView = null;
    private NodeObject profileView = null;
    private NodeObject gameView = null;
    private NodeObject offlinedictionaryView = null;
    private NodeObject rankView = null;
    private NodeObject flashCardView = null;

    private NodeObject currentPanel = null;
    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ApplicationData.getInstance().loadAllData();
        setupHomeView();
        setupDictionaryView();
        setupProfileView();
        setupGameView();
        setupOfflineDictionaryView();
        setupRankView();
        setupFlashCardView();
        EquizUtils.callFuncDelay(this::setupButton, 1000);

        // Set default panel to home
        currentPanel = homeView;

        logOutButton.setOnAction((ActionEvent e) -> {
            StageManager.getInstance().myApplicationStage.hide();
            StageManager.getInstance().loginStage.show();
            ControllerManager.getInstance().loginController.tfLogin_username.setText(null);
            ControllerManager.getInstance().loginController.tfLogin_showPass.setText(null);
            ControllerManager.getInstance().loginController.pfLogin_password.setText(null);

            try {
                Socket socket = ApplicationData.getInstance().socket;
                if (socket.isConnected()) {
                    socket.close();
                    ObjectOutputStream oos = ClientHelperRequest.objectOutputStream;
                    if(oos != null){
                        oos.close();
                    }
                    ClientHelperRequest.objectOutputStream = null;
                }
            } catch (Exception ignore) {
            }
        });
    }

    public void setupButton() {
        Stage stage = ((Stage) this.btnClose.getScene().getWindow());
        this.btnClose.setOnAction((ActionEvent event) -> {
            stage.close();
            try {
                ApplicationData.getInstance().socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.hboxUpperBar.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });

        this.hboxUpperBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });
    }

    private void setupHomeView() {
        // Add home panel to application.
        try {
            homeView = EquizUtils.Instantiate("/view/HomeView.fxml", panelHolder, AnchorType.FitToParent);
            homeView.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupDictionaryView() {
        try {
            dictionaryView = EquizUtils.Instantiate("/view/DictionaryView.fxml");
            panelHolder.getChildren().add(dictionaryView.getNode());
            dictionaryView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupOfflineDictionaryView() {
        try {
            offlinedictionaryView = EquizUtils.Instantiate("/view/OfflineDictionaryView.fxml", panelHolder, AnchorType.FitToParent);
            offlinedictionaryView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupProfileView() {
        try {
            // Load profile here.
            profileView = EquizUtils.Instantiate("/view/ProfileContainerView.fxml", panelHolder, AnchorType.FitToParent);
            profileView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupGameView() {
        try {
            // Load profile here.
            gameView = EquizUtils.Instantiate("/view/game/GameView.fxml", panelHolder, AnchorType.FitToParent);
            gameView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupRankView() {
        try {
            // Load profile here.
            rankView = EquizUtils.Instantiate("/view/login/RankView.fxml", panelHolder, AnchorType.FitToParent);
            rankView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupFlashCardView() {
        try {
            // Load profile here.
            flashCardView = EquizUtils.Instantiate("/view/FlashCardView.fxml", panelHolder, AnchorType.FitToParent);
            flashCardView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchToPanel(NodeObject panel) {
        if (currentPanel != null) {
            currentPanel.setVisible(false);
        }

        currentPanel = panel;

        if (currentPanel != null) {
            currentPanel.setVisible(true);
        }
    }

    public void onClickSwitchToHome() {
        switchToPanel(homeView);
    }

    public void onClickSwitchToDictionary() {
        switchToPanel(dictionaryView);
    }

    public void onClickSwitchToProfile() {
        switchToPanel(profileView);
    }

    public void onClickSwitchToOfflineDictionary() {
        switchToPanel(offlinedictionaryView);
    }

    public void onClickSwitchToGame() {
        switchToPanel(gameView);
    }

    public void onClickSwitchToRank() {
        switchToPanel(rankView);
    }

    public void onClickSwitchToFlashCard() {
        switchToPanel(flashCardView);
    }
}

