package com.dga.equiz.controller;

import com.dga.equiz.model.EquizUtils;
import com.dga.equiz.model.Home;
import com.dga.equiz.model.NodeObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public final class HomeController implements Initializable {

    //region Singleton
    private static HomeController instance;
    public static HomeController getInstance(){
        return instance;
    }
    //endregion

    //region FXML Reference
    @FXML
    private VBox campaginList;

    @FXML
    private AnchorPane campaignOption;
    //endregion

    //region MVC
    private final Home homeModel = new Home();
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(instance == null){
            instance = new HomeController();
        }

        try{
            for(int i = 0; i < 3; i++) {
                NodeObject root = EquizUtils.Instantiate("/view/CampaignView.fxml");
                campaginList.getChildren().add(root.getNode());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setCampaignOption(boolean isVisible) {
        campaignOption.setVisible(isVisible);
    }
}
