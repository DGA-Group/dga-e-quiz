package com.dga.equiz;

import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.StageManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            loadStage();

        } catch (Exception e) {
            if (ApplicationData.getInstance().socket != null) {
                ApplicationData.getInstance().socket.close();
            }
            stage.close();
            e.printStackTrace();
        }
    }

    private void addStyle(Scene scene, String cssPath) {
        scene.getStylesheets().add(String.valueOf(Main.class.getResource(cssPath)));
    }

    private void loadStage() throws IOException {
        Platform.runLater(() -> {
            NodeObject loginView = null; // MyApplication.fxml;
            try {
                loginView = EquizUtils.Instantiate("/view/login/Login.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene loginScene = new Scene((Parent) loginView.getNode(), 648, 430, Color.TRANSPARENT);
            Stage loginStage = StageManager.getInstance().loginStage = new Stage();
            loginStage.initStyle(StageStyle.TRANSPARENT);
            loginStage.setScene(loginScene);
            loginStage.show();
        });
    }

    public static void main(String[] args) {
        launch();
    }


}
