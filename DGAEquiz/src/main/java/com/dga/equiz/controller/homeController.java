package com.dga.equiz.controller;

import com.dga.equiz.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeController implements Initializable {

    @FXML
    private VBox campaginList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Node root = FXMLLoader.load(Main.class.getResource("/view/campaign.fxml"));
            Node root2 = FXMLLoader.load(Main.class.getResource("/view/campaign.fxml"));
            Node root3 = FXMLLoader.load(Main.class.getResource("/view/campaign.fxml"));

            campaginList.getChildren().add(root);
            campaginList.getChildren().add(root2);
            campaginList.getChildren().add(root3);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void init() {

    }
}
