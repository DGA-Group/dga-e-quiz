package com.dga.equiz;

import com.dga.equiz.controller.MyApplicationController;
import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.model.nodeObject.NodeObject;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.lang.reflect.Field;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            NodeObject root = EquizUtils.Instantiate("/view/MyApplication.fxml");
            MyApplicationController controller = root.getController();
            controller.setupButton(stage);
            Scene scene = new Scene((Parent) root.getNode(), 854, 480, Color.TRANSPARENT);
            load(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load(Scene scene) {
        scene.getStylesheets().add(String.valueOf(Main.class.getResource("/css/learnDesign.css")));
    }

    public static void main(String[] args) {
        launch();
    }


}
