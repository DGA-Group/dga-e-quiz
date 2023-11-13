package com.dga.equiz.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OfflineDictionaryController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField searchingField;

    @FXML
    private Label labelMeaning;
    @FXML
    private MenuButton buttonMenu;
    @FXML
    private MenuItem item1;
    @FXML
    private MenuItem item2;
    @FXML
    private Label labelPhonetic;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonSearch;
    @FXML
    private VBox wordsBox;

    public OfflineDictionaryController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
