package com.dga.equiz.controller;

import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.EquizUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Dictionary;
import java.util.ResourceBundle;

public class MyApplicationController implements Initializable {

    //region FXML Reference
    @FXML
    private AnchorPane panelHolder;
    //endregion

    private NodeObject homeView = null;
    private NodeObject dictionaryView = null;
    private NodeObject profileView = null;

    private NodeObject currentPanel = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ApplicationData.getInstance().loadAllData();
        setupHomeView();
        setupDictionaryView();
        setupProfileView();

        // Set default panel to home
        currentPanel = homeView;
    }

    private void setupHomeView() {
        // Add home panel to application.
        try {
            homeView = EquizUtils.Instantiate("/view/HomeView.fxml");
            panelHolder.getChildren().add(homeView.getNode());
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
            profileView = EquizUtils.Instantiate("/view/ProfileContainerView.fxml");
            panelHolder.getChildren().add(profileView.getNode());

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
