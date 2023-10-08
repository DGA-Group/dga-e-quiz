package com.dga.equiz.controller.campaign;

import com.dga.equiz.controller.home.HomeController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public final class CampaignPickerController implements Initializable {

    //region Singleton
    private static CampaignPickerController instance;
    public static CampaignPickerController getInstance() {
        return instance;
    }
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        System.gc();
    }

    public void onClickOpenLearnPanel(){
        HomeController.getInstance().openLearnPanel();
    }
}
