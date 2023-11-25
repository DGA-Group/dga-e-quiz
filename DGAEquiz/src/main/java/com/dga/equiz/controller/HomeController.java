package com.dga.equiz.controller;

import com.dga.equiz.controller.campaign.CampaignController;
import com.dga.equiz.controller.campaign.FinishCampaignController;
import com.dga.equiz.controller.login.RankController;
import com.dga.equiz.model.Campaign;
import com.dga.equiz.model.Lesson;
import com.dga.equiz.model.Profile;
import com.dga.equiz.model.event.IEventLong;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.ApplicationEnum.AnchorType;
import com.dga.equiz.utils.ControllerManager;
import com.dga.equiz.utils.DBHelper;
import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.model.nodeObject.NodeObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

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

    @FXML
    private Label lbUserName;

    @FXML
    private Rectangle rectUserAvatar;

    @FXML
    private Label lbPoint;

    @FXML
    private Label lbRankTop;

    @FXML
    private Label lbFlashCard;
    //endregion

    // region Model
    private NodeObject currentPanel = null;
    private NodeObject campaignPickerView = null;
    private NodeObject learnView = null;
    private NodeObject finishView = null;

    private Map<Long, CampaignController> campaigns = new HashMap<>();
    //endregion

    // region Event
    public IEventLong onFinishCampaign = campaignNumber -> {
        Profile profile = ApplicationData.getInstance().profile;
        int userId = profile.getID();
        String username = profile.getUsername();
        int currentPoint = profile.getScore();
        long currentCampaign = profile.getCurrentCampaign();
        int totalCampaign = ApplicationData.getInstance().getCampaignData().size();

        // Add point
        if (campaignNumber == currentCampaign) {
            int rewardPoints = 10;
            int newPoint = currentPoint + rewardPoints;
            String sql = "UPDATE information SET score = '" + newPoint
                    + "' WHERE username = '" + username + "'";
            try {
                DBHelper.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RankController rankController = ControllerManager.getInstance().rankController;
            String userNewRank = "Top " + rankController.findUserRank(userId);
            setUserPoints(newPoint + "");
            setRank(userNewRank);
            ControllerManager.getInstance().rankController.reloadRank();
        }

        if (campaignNumber == currentCampaign && campaignNumber < totalCampaign) {
            // Set next campaign
            long nextCampaignNumber = campaignNumber + 1;
            CampaignController nextCampaignController = campaigns.get(nextCampaignNumber);
            nextCampaignController.setUnlockCampaign();
            String sql = "UPDATE information SET current_campaign = '" + nextCampaignNumber
                    + "' WHERE username = '" + username + "'";
            try {
                DBHelper.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Unlock next campaign
            nextCampaignController.setUnlockCampaign();

            // Switch to finish view
            switchToPanel(finishView);
        }
    };

    //endregion


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManager.getInstance().homeController = this;
        setupHome();
        setupCampaignList();
        setupCampaignPickerView();
        setupLearnView();
        setupFinishView();
    }

    private void setupHome() {
        Profile profile = ApplicationData.getInstance().profile;
        int userId = profile.getID();
        String name = profile.getName();
        try {
            Image userAva = profile.getUserAva();
            rectUserAvatar.setFill(new ImagePattern(userAva));
            rectUserAvatar.setOnMouseClicked(mouseEvent -> {
                MyApplicationController controller = ControllerManager.getInstance().myApplicationController;
                controller.onClickSwitchToProfile();
            });
            lbUserName.setText(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RankController rankController = ControllerManager.getInstance().rankController;
        FlashCardController flashCardController = ControllerManager.getInstance().flashCardController;
        String userPoints = rankController.findUserPoint(userId) + " points";
        String userRank = "Top " + rankController.findUserRank(userId);
        String flashCards = flashCardController.findUserFlashCards(userId) + " cards";
        setUserPoints(userPoints);
        setRank(userRank);
        setFlashCard(flashCards);
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
                controller.buttonStartCampaign.setOnAction(event -> {
                    LearnController learnController = learnView.getController();
                    Lesson lesson = campaign.getLesson();
                    learnController.onFinishCampaign = onFinishCampaign;
                    learnController.setLesson(lesson, campaignId);
                    switchToPanel(learnView);
                });


                if (campaignId <= currentCampaign) {
                    controller.setUnlockCampaign();
                }

                campaigns.put(campaignId, controller);
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
            controller.buttonClose.setOnAction(event -> switchToPanel(null));
            controller.onGoToFinishView = () -> switchToPanel(finishView);
            learnView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupCampaignPickerView() {
        try {
            // Add campaign picker panel to home.
            campaignPickerView = EquizUtils.Instantiate("/view/campaign/CampaignPickerView.fxml", panelHome, AnchorType.FitToParent);
            campaignPickerView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupFinishView() {
        try {
            finishView = EquizUtils.Instantiate("/view/campaign/FinishCampaignView.fxml", panelHome, AnchorType.FitToParent);
            FinishCampaignController controller = finishView.getController();

            controller.btnToHome.setOnAction(event -> switchToPanel(null));

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

    public void setUserPoints(String points) {
        lbPoint.setText(points);
    }

    public void setRank(String rank) {
        lbRankTop.setText(rank);
    }

    public void setFlashCard(String flashCard) {
        lbFlashCard.setText(flashCard);
    }
}
