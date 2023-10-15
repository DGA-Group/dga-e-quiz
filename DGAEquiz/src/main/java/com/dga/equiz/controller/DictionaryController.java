package com.dga.equiz.controller;
import  java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import  javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;
public class DictionaryController implements Initializable {
    @FXML
    private  AnchorPane root;
    @FXML
    private  TextField words;
    public  void onStartup() throws IOException{
        TextFields.bindAutoCompletion(words,
                "Duong Vip Pro", "Duongqua", "dm");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            onStartup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

