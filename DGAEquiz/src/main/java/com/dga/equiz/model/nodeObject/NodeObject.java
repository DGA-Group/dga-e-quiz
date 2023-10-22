package com.dga.equiz.model.nodeObject;

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

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public <T> T getController() {
        return fxmlLoader.getController();
    }

    /**
     * Set visible for the node.
     *
     * @param isVisible The node is visible if true, invisible if false.
     */
    public void setVisible(boolean isVisible) {
        node.setVisible(isVisible);
    }

    /**
     * Show the node.
     */
    public void show() {
        setVisible(true);
    }

    /**
     * Hide the node.
     */
    public void hide() {
        setVisible(false);
    }
}
