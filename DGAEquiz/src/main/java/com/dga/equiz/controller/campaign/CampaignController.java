package com.dga.equiz.controller.campaign;

import com.dga.equiz.model.Campaign;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CampaignController {

    //region FXML Reference
    @FXML
    public Button buttonStartCampaign;

    @FXML
    public Label labelTitle;

    @FXML
    public Label labelDescription;
    //endregion


    private Campaign campaignModel;

    public void setCampaignModel(Campaign campaignModel) {
        this.campaignModel = campaignModel;
    }

    public void setTitle(String title) {
        this.labelTitle.setText(title);
    }

    public void setDescription(String description) {
        this.labelDescription.setText(description);
    }

    public void setupCampaign(Campaign campaignModel){
        this.labelTitle.setText(campaignModel.getTitle());
        this.labelDescription.setText(campaignModel.getDescription());
    }
}
