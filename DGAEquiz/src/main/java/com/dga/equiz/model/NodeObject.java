package com.dga.equiz.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class NodeObject {
    private Node node;
    private FXMLLoader fxmlLoader;

    public NodeObject(Node node, FXMLLoader fxmlLoader) {
        this.node = node;
        this.fxmlLoader = fxmlLoader;
    }

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public void setFxmlLoader(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public <T> T getController() {
        return fxmlLoader.getController();
    }
}
