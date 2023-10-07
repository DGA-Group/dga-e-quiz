package com.dga.equiz.controller.home;

import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.model.home.Home;
import com.dga.equiz.model.nodeObject.NodeObject;
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
    private AnchorPane panelHome;

    @FXML
    private VBox vBCampaignList;
    //endregion

    //region MVC
    private final Home homeModel = new Home();
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        System.gc();

        try{
            for(int i = 0; i < 3; i++) {
                NodeObject root = EquizUtils.Instantiate("/view/campaign/CampaignView.fxml");
                vBCampaignList.getChildren().add(root.getNode());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
