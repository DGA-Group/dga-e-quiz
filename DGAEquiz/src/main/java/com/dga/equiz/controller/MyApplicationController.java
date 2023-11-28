package com.dga.equiz.controller;

import com.dga.equiz.model.FlashCard;
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
import javafx.scene.layout.VBox;
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
    private VBox vBoxButtonTab;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnOnlineDictionary;

    @FXML
    private Button btnOfflineDictionary;

    @FXML
    private Button btnGame;

    @FXML
    private Button btnFlashcard;

    @FXML
    private Button btnRank;

    @FXML
    private Button btnProfile;

    @FXML
    private AnchorPane paneLogo;

    //endregion

    private NodeObject homeView = null;
    private NodeObject dictionaryView = null;
    private NodeObject profileView = null;
    private NodeObject gameView = null;
    private NodeObject offlineDictionaryView = null;
    private NodeObject rankView = null;
    private NodeObject flashCardView = null;

    private NodeObject currentPanel = null;
    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManager.getInstance().myApplicationController = this;
    }

    public void loadOnlineProgram() {
        ApplicationData.getInstance().loadAllData();

        setupDictionaryView();

        setupProfileView();

        setupGameView();

        setupOfflineDictionaryView();

        setupRankView();

        setupFlashCardView();

        setupHomeView();

        EquizUtils.callFuncDelay(this::setupButton, 1000);
        // Set default panel to home
        currentPanel = homeView;

        logOutButton.setOnAction(event -> {
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
                    if (oos != null) {
                        oos.close();
                    }
                    ClientHelperRequest.objectOutputStream = null;
                }
            } catch (Exception ignore) {
            }
        });

        loadTabButton(true);
    }

    public void loadOfflineProgram() {
        setupOfflineDictionaryView();
        offlineDictionaryView.show();

        logOutButton.setOnAction(event -> {
            StageManager.getInstance().myApplicationStage.hide();
            StageManager.getInstance().loginStage.show();
        });

        EquizUtils.callFuncDelay(this::setupButton, 1000);
        // Set default panel to home
        currentPanel = offlineDictionaryView;
        loadTabButton(false);
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
    }

    private void setupHomeView() {
        // Add home panel to application.
        try {
            if (ControllerManager.getInstance().homeController == null) {
                homeView = EquizUtils.Instantiate("/view/HomeView.fxml", panelHolder, AnchorType.FitToParent);
            }
            homeView.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupDictionaryView() {
        try {
            if (ControllerManager.getInstance().dictionaryController == null) {
                dictionaryView = EquizUtils.Instantiate("/view/DictionaryView.fxml");
            }
            panelHolder.getChildren().add(dictionaryView.getNode());
            dictionaryView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupOfflineDictionaryView() {
        try {
            if (ControllerManager.getInstance().offlineDictionaryController == null) {
                offlineDictionaryView = EquizUtils.Instantiate("/view/OfflineDictionaryView.fxml", panelHolder, AnchorType.FitToParent);
            }
            offlineDictionaryView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupProfileView() {
        try {
            // Load profile here.
            if (ControllerManager.getInstance().profileController == null) {
                profileView = EquizUtils.Instantiate("/view/ProfileContainerView.fxml", panelHolder, AnchorType.FitToParent);
            }
            profileView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupGameView() {
        try {
            // Load profile here.
            if (ControllerManager.getInstance().gameController == null) {
                gameView = EquizUtils.Instantiate("/view/game/GameView.fxml", panelHolder, AnchorType.FitToParent);
            }
            gameView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupRankView() {
        try {
            // Load profile here.
            if (ControllerManager.getInstance().rankController == null) {
                rankView = EquizUtils.Instantiate("/view/login/RankView.fxml", panelHolder, AnchorType.FitToParent);
            }
            rankView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupFlashCardView() {
        try {
            // Load profile here.
            if (ControllerManager.getInstance().flashCardController == null) {
                flashCardView = EquizUtils.Instantiate("/view/FlashCardView.fxml", panelHolder, AnchorType.FitToParent);
            }
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
        switchToPanel(offlineDictionaryView);
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

    private void loadTabButton(boolean isOnline) {
        vBoxButtonTab.getChildren().clear();
        vBoxButtonTab.getChildren().add(paneLogo);
        if (isOnline) {
            vBoxButtonTab.getChildren().add(btnDashboard);
            vBoxButtonTab.getChildren().add(btnOnlineDictionary);
            vBoxButtonTab.getChildren().add(btnOfflineDictionary);
            vBoxButtonTab.getChildren().add(btnGame);
            vBoxButtonTab.getChildren().add(btnFlashcard);
            vBoxButtonTab.getChildren().add(btnRank);
            vBoxButtonTab.getChildren().add(btnProfile);
            vBoxButtonTab.getChildren().add(logOutButton);
            vBoxButtonTab.getChildren().add(btnClose);
        } else {
            vBoxButtonTab.getChildren().add(btnOfflineDictionary);
            vBoxButtonTab.getChildren().add(logOutButton);
            vBoxButtonTab.getChildren().add(btnClose);
        }
    }
}

