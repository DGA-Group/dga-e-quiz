package com.dga.equiz.controller;

import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.EquizUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MyApplicationController implements Initializable {

    //region Singleton
    private static MyApplicationController instance;

    public static MyApplicationController getInstance() {
        return instance;
    }
    //endregion

    //region FXML Reference
    @FXML
    private AnchorPane panelHolder;
    //endregion

    private NodeObject homeView = null;
    private NodeObject dictionaryView = null;
    private NodeObject profileView = null;
    private NodeObject campaignPickerView = null;

    private NodeObject currentPanel = null;


    //region Getter and Setter
    public NodeObject getCampaignPickerView() {
        return campaignPickerView;
    }
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        System.gc();

        // Initialize program here.
        try {
            this.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize everything here before run the application.
     *
     * @throws IOException Throw exception if cannot initialize.
     */
    private void init() throws IOException {
        // Add home panel to application.
        homeView = EquizUtils.Instantiate("/view/home/HomeView.fxml");
        panelHolder.getChildren().add(homeView.getNode());
        homeView.show();

        // Add campaign picker panel to application.
        campaignPickerView = EquizUtils.Instantiate("/view/campaign/CampaignPickerView.fxml");
        panelHolder.getChildren().add(campaignPickerView.getNode());
        campaignPickerView.hide();

        // Add more panel here.

        // Set default panel to home
        currentPanel = homeView;
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

    public void openCampaignPicker(){
        switchToPanel(campaignPickerView);
    }
}
