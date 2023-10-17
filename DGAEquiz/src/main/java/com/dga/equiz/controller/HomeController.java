package com.dga.equiz.controller;

import com.dga.equiz.controller.campaign.CampaignController;
import com.dga.equiz.controller.campaign.CampaignPickerController;
import com.dga.equiz.model.Campaign;
import com.dga.equiz.utils.ApplicationData;
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

    private final Home homeModel = new Home();
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
    private final EventHandler<ActionEvent> hideLearnView = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            learnView.hide();
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
        setupCampaignPickerView();
        setupCampaignList();
        setupLearnView();
    }

    private void setupHome() {

    }

    private void setupLearnView() {
        // Add learn panel to home.
        try {
            learnView = EquizUtils.Instantiate("/view/LearnView.fxml");
            panelHome.getChildren().add(learnView.getNode());
            LearnController controller = learnView.getController();
            controller.buttonClose.setOnAction(hideLearnView);
            learnView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupCampaignPickerView() {
        try {
            // Add campaign picker panel to home.
            campaignPickerView = EquizUtils.Instantiate("/view/campaign/CampaignPickerView.fxml");
            panelHome.getChildren().add(campaignPickerView.getNode());
            CampaignPickerController controller = campaignPickerView.getController();
            controller.buttonLearn.setOnAction(showLearnView);
            controller.buttonRevise.setOnAction(showLearnView);
            campaignPickerView.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setupCampaignList() {
        try {
            // Add campaign to to home
            Map<Long, Campaign> campaignData = ApplicationData.getInstance().getCampaignData();
            for (var campaign : campaignData.values()) {
                NodeObject node = EquizUtils.Instantiate("/view/campaign/CampaignView.fxml");

                CampaignController controller = node.getController();
                String title = campaign.getTitle();
                String description = campaign.getDescription();
                controller.setupCampaign(title, description);

                controller.startCampaign.setOnAction(showCampaignPickerView);

                vBCampaignList.getChildren().add(node.getNode());
            }
            vBCampaignList.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
