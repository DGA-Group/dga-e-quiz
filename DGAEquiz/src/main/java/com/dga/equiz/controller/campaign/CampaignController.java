package com.dga.equiz.controller.campaign;

import com.dga.equiz.controller.home.HomeController;

public class CampaignController {

    public void onClickOpenCampaign() {
        HomeController.getInstance().openCampaignPicker();
    }
}
