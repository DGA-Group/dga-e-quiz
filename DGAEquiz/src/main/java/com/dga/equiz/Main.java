package com.dga.equiz;

import com.dga.equiz.model.EquizUtils;
import com.dga.equiz.model.NodeObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try{
            NodeObject root = EquizUtils.Instantiate("/view/home.fxml");
            Scene scene = new Scene((Parent) root.getNode());
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }


}
