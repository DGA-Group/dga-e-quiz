package com.dga.equiz.controller.home;

import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.model.home.Home;
import com.dga.equiz.model.nodeObject.NodeObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public final class HomeController implements Initializable {

    //region Singleton
    private static HomeController instance;

    public static HomeController getInstance() {
        return instance;
    }
    //endregion

    //region FXML Reference
    @FXML
    private AnchorPane panelHome;

    @FXML
    private VBox vBCampaignList;
    //endregion

    private final Home homeModel = new Home();
    private NodeObject campaignPickerView = null;
    private NodeObject learnView = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        System.gc();

        try {
            init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        // Add campaign picker panel to home.
        campaignPickerView = EquizUtils.Instantiate("/view/campaign/CampaignPickerView.fxml");
        panelHome.getChildren().add(campaignPickerView.getNode());
        campaignPickerView.hide();

        // Add learn panel to home.
        learnView = EquizUtils.Instantiate("/view/learn/LearnView.fxml");
        panelHome.getChildren().add(learnView.getNode());
        learnView.hide();

        // Add campaign to to home
        for (int i = 0; i < 2; i++) {
            NodeObject node = EquizUtils.Instantiate("/view/campaign/CampaignView.fxml");
            vBCampaignList.getChildren().add(node.getNode());
        }
        vBCampaignList.setVisible(true);
    }

    public void openCampaignPicker() {
        campaignPickerView.show();
    }

    public void openLearnPanel() {
        learnView.show();
    }
}
