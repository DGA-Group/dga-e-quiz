package com.dga.equiz.controller;

import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.ApplicationEnum.AnchorType;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.Event;

import java.net.URL;
import java.util.ResourceBundle;

public class MyApplicationController implements Initializable {

    //region FXML Reference
    @FXML
    private AnchorPane panelHolder;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMaximize;

    @FXML
    private Button btnMinimize;

    @FXML
    private HBox hboxUpperBar;
    //endregion

    private NodeObject homeView = null;
    private NodeObject dictionaryView = null;
    private NodeObject profileView = null;

    private NodeObject currentPanel = null;
    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ApplicationData.getInstance().loadAllData();
        setupHomeView();
        setupDictionaryView();
        setupProfileView();

        // Set default panel to home
        currentPanel = homeView;
    }

    public void setupButton(Stage stage) {
        this.btnClose.setOnAction((ActionEvent event) -> {
            stage.close();
        });

        this.btnMaximize.setOnAction((ActionEvent event) -> {
            stage.setMaximized(!stage.isMaximized());
        });

        this.btnMinimize.setOnAction((ActionEvent event) -> {
            stage.setIconified(true);
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

    private void setupProfileView() {
        try {
            // Load profile here.
            profileView = EquizUtils.Instantiate("/view/ProfileContainerView.fxml", panelHolder, AnchorType.FitToParent);
            //profileView = EquizUtils.Instantiate("/view/login/Login.fxml", panelHolder, AnchorType.FitToParent);
            profileView.hide();
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
}