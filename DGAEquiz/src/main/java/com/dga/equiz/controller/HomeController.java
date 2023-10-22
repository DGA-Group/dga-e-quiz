package com.dga.equiz.controller;

import com.dga.equiz.controller.campaign.CampaignController;
import com.dga.equiz.controller.campaign.CampaignPickerController;
import com.dga.equiz.model.Campaign;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.ApplicationEnum.AnchorType;
import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.model.Home;
import com.dga.equiz.model.nodeObject.NodeObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    //region FXML Reference
    @FXML
    private AnchorPane panelHome;

    @FXML
    private VBox vBCampaignList;
    //endregion

    private Home homeModel = new Home();
    private NodeObject currentPanel = null;
    private NodeObject campaignPickerView = null;
    private NodeObject learnView = null;

    //region Event
    private final EventHandler<ActionEvent> showLearnView = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            learnView.show();
            campaignPickerView.hide();
        }
    };
    private final EventHandler<ActionEvent> showCampaignPickerView = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            campaignPickerView.show();
        }
    };
    //endregion


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupHome();
        setupCampaignList();
        setupCampaignPickerView();
        setupLearnView();
    }

    private void setupHome() {

    }

    private void setupCampaignList() {
        try {
            // Add campaign to to home
            Map<Long, Campaign> campaignData = ApplicationData.getInstance().getCampaignData();
            for (var campaign : campaignData.values()) {
                NodeObject node = EquizUtils.Instantiate("/view/campaign/CampaignView.fxml", vBCampaignList);
                CampaignController controller = node.getController();
                controller.setCampaignModel(campaign);
                controller.setupCampaign(campaign);
                controller.buttonStartCampaign.setOnAction((ActionEvent event) -> {
                    LearnController learnController = learnView.getController();
                    learnController.setLesson(campaign.getLesson());
                    switchToPanel(campaignPickerView);
                });
            }
            vBCampaignList.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupLearnView() {
        // Add learn panel to home.
        try {
            learnView = EquizUtils.Instantiate("/view/LearnView.fxml", panelHome, AnchorType.FitToParent);
            LearnController controller = learnView.getController();
            controller.buttonClose.setOnAction((ActionEvent event) -> {
                switchToPanel(null);
            });
            learnView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupCampaignPickerView() {
        try {
            // Add campaign picker panel to home.
            campaignPickerView = EquizUtils.Instantiate("/view/campaign/CampaignPickerView.fxml", panelHome, AnchorType.FitToParent);
            CampaignPickerController controller = campaignPickerView.getController();

            controller.buttonLearn.setOnAction((ActionEvent event) -> {
                switchToPanel(learnView);
            });

            campaignPickerView.hide();
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
}
