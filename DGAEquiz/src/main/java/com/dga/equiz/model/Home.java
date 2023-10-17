package com.dga.equiz.model;

import java.util.ArrayList;
import java.util.List;

public class Home {
    // List of campaign that currently available
    private List<Campaign> campaigns = new ArrayList<Campaign>();

    public void addCampaign(Campaign newCampaign) {
        if (newCampaign == null) {
            System.out.println("New campaign is null!");
            return;
        }
        campaigns.add(newCampaign);
    }

    public void removeCampaign(long campaignNumber) {
        campaigns.removeIf(campaign -> campaign.getCampaignNumber() == campaignNumber);
    }
}
