package com.dga.equiz;

import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.EquizUtils;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.game.ClientHelperRequest;
import com.dga.game.EquizPacket.Client.ConnectClientRequest;
import com.dga.game.ClientListener;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            NodeObject root = EquizUtils.Instantiate("/view/MyApplication.fxml");
            Scene scene = new Scene((Parent) root.getNode(), 854, 480, Color.TRANSPARENT);
            loadStyle(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

            /*
            Socket socket = new Socket("127.0.0.1", 54321);
            ApplicationData.getInstance().socket = socket;
            EquizUtils.callFuncDelay((event -> {ClientHelperRequest.sendRequest(new ConnectClientRequest("" + ApplicationData.getInstance().profile.getID()));}), 1000);
            new ClientListener(socket).start();
            System.out.println("Success connect to equiz server at port 54321...");
            */
        } catch (Exception e) {
            if (ApplicationData.getInstance().socket != null) {
                ApplicationData.getInstance().socket.close();
            }
            stage.close();
            e.printStackTrace();
        }
    }

    private void loadStyle(Scene scene) {
        scene.getStylesheets().add(String.valueOf(Main.class.getResource("/css/learnDesign.css")));
    }

    public static void main(String[] args) {
        launch();
    }


}
