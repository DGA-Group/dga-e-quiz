package com.dga.equiz.controller;

import com.dga.equiz.model.Campaign;

public class CampaignController {

    //region MVC
    private final Campaign campaign = new Campaign();
    //endregion

    public void OnClickOpenCampaign() {
        HomeController.getInstance().setCampaignOption(true);
    }
}
