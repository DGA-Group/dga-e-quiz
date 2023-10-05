package com.dga.equiz.model;

import com.dga.equiz.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class EquizUtils {
    /**
     * Instantiate a NodeObject.
     * @param path Path to FXML file.
     * @return New NodeObject.
     * @throws IOException Throw IOException if NodeObject is not able to be created.
     */
    public static NodeObject Instantiate(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
        Node node = fxmlLoader.<Node>load();
        return new NodeObject(node, fxmlLoader);
    }
}
