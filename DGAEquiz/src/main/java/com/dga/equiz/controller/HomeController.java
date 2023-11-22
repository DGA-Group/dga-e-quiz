package com.dga.equiz.controller;

import com.dga.equiz.controller.campaign.CampaignController;
import com.dga.equiz.controller.campaign.CampaignPickerController;
import com.dga.equiz.controller.campaign.FinishCampaignController;
import com.dga.equiz.model.Campaign;
import com.dga.equiz.model.Lesson;
import com.dga.equiz.model.Profile;
import com.dga.equiz.model.event.IEventLong;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.ApplicationEnum.AnchorType;
import com.dga.equiz.utils.DBHelper;
import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.model.nodeObject.NodeObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    //region FXML Reference
    @FXML
    private AnchorPane panelHome;

    @FXML
    private VBox vBCampaignList;
    //endregion

    private NodeObject currentPanel = null;
    private NodeObject campaignPickerView = null;
    private NodeObject learnView = null;
    private NodeObject finishView = null;

    private Map<Long, CampaignController> campaigns = new HashMap<>();

    //endregion


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupHome();
        setupCampaignList();
        setupCampaignPickerView();
        setupLearnView();
        setupFinishView();
    }

    private void setupHome() {

    }

    private void setupCampaignList() {
        try {
            // Add campaign to to home
            Profile profile = ApplicationData.getInstance().profile;
            long currentCampaign = profile.getCurrentCampaign();
            Map<Long, Campaign> campaignData = ApplicationData.getInstance().getCampaignData();
            for (var campaign : campaignData.values()) {
                NodeObject node = EquizUtils.Instantiate("/view/campaign/CampaignView.fxml", vBCampaignList);
                long campaignId = campaign.getId();
                CampaignController controller = node.getController();
                controller.setCampaignModel(campaign);
                controller.setupCampaign(campaign);
                controller.buttonStartCampaign.setOnAction((ActionEvent event) -> {
                    LearnController learnController = learnView.getController();
                    Lesson lesson = campaign.getLesson();
                    learnController.setLesson(lesson, campaignId);
                    learnController.onFinishCampaign = onFinishCampaign;
                    switchToPanel(campaignPickerView);
                });

                if (campaignId <= currentCampaign) {
                    controller.setUnlockCampaign();
                }
            }
            vBCampaignList.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupLearnView() {
        // Add learn panel to home.
        try {
            learnView = EquizUtils.Instantiate("/view/LearnView.fxml", panelHome);
            LearnController controller = learnView.getController();
            controller.buttonClose.setOnAction((ActionEvent event) -> {
                switchToPanel(null);
            });
            controller.onGoToFinishView = () -> {
                switchToPanel(finishView);
            };
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

    private void setupFinishView() {
        try {
            finishView = EquizUtils.Instantiate("/view/campaign/FinishCampaignView.fxml", panelHome, AnchorType.FitToParent);
            FinishCampaignController controller = finishView.getController();

            controller.btnToHome.setOnAction((ActionEvent event) -> {
                switchToPanel(null);
            });

            finishView.hide();
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


    public IEventLong onFinishCampaign = campaignNumber -> {
        Profile profile = ApplicationData.getInstance().profile;
        String username = profile.getUsername();
        int currentPoint = profile.getScore();
        long currentCampaign = profile.getCurrentCampaign();
        int totalCampaign = ApplicationData.getInstance().getCampaignData().size();

        // Add point
        int rewardPoints = 10;
        int newPoint = currentPoint + rewardPoints;
        String sql = "";
        sql = "UPDATE information SET score = '" + newPoint
                + "' WHERE username = '" + username + "'";
        try {
            DBHelper.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (campaignNumber < currentCampaign || campaignNumber >= totalCampaign) {
            return;
        }

        // Set next campaign
        long nextCampaignNumber = campaignNumber + 1;
        CampaignController nextCampaignController = campaigns.get(nextCampaignNumber);
        nextCampaignController.setUnlockCampaign();
        sql = "UPDATE information SET current_campaign = '" + nextCampaignNumber
                + "' WHERE username = '" + username + "'";
        try {
            DBHelper.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Unlock next campaign
        nextCampaignController.setUnlockCampaign();
    };


}
