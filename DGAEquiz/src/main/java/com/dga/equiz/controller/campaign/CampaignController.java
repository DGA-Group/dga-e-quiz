package com.dga.equiz.controller.campaign;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CampaignController {

    //region FXML Reference
    @FXML
    public Button startCampaign;

    @FXML
    public Label labelTitle;

    @FXML
    public Label labelDescription;
    //endregion

    public void setTitle(String title) {
        this.labelTitle.setText(title);
    }

    public void setDescription(String description) {
        this.labelDescription.setText(description);
    }

    public void setupCampaign(String title, String description){
        this.labelTitle.setText(title);
        this.labelDescription.setText(description);
    }
}
