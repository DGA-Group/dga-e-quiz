package com.dga.equiz;

import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.model.nodeObject.NodeObject;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.lang.reflect.Field;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            NodeObject root = EquizUtils.Instantiate("/view/MyApplication.fxml");
            Scene scene = new Scene((Parent) root.getNode());
            load(scene);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load(Scene scene) {
        scene.getStylesheets().add("/css/learnDesign.css");
    }

    public static void main(String[] args) {
        launch();
    }


}
