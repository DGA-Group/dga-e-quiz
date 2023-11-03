package com.dga.equiz.controller.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button buttonAcc_go;

    @FXML
    private Button buttonAcc_back;

    @FXML
    private Button buttonLogin_ForgotPass;

    @FXML
    private Button buttonLogin_login;

    @FXML
    private Button buttonLogin_signUp;

    @FXML
    private Button buttonPass_go;

    @FXML
    private Button buttonForgot_go;

    @FXML
    private Button buttonForgot_back;

    @FXML
    private Button buttonRegister_next;

    @FXML
    private Button buttonRegister_back;

    @FXML
    private CheckBox checkAcc_agree;

    @FXML
    private CheckBox checkAcc_showAcc;

    @FXML
    private CheckBox checkLogin_pass;

    @FXML
    private DatePicker dateRegister_dob;

    @FXML
    private Label labelLogin_WrongPass;

    @FXML
    private StackPane stackPane;

    @FXML
    private BorderPane paneConfirmAcc;

    @FXML
    private BorderPane paneConfirmPass;

    @FXML
    private BorderPane paneForgotPass;

    @FXML
    private BorderPane paneLogin;

    @FXML
    private BorderPane paneRegister;

    @FXML
    private TextField tfAcc_code1;

    @FXML
    private TextField tfAcc_code2;

    @FXML
    private TextField tfAcc_code3;

    @FXML
    private TextField tfAcc_code4;

    @FXML
    private TextField tfAcc_confirmPass;

    @FXML
    private TextField tfForgot_Npass;

    @FXML
    private TextField tfForgot_confNpass;

    @FXML
    private TextField tfForgot_mail;

    @FXML
    private TextField tfLogin_password;

    @FXML
    private TextField tfLogin_username;

    @FXML
    private TextField tfPass_code1;

    @FXML
    private TextField tfPass_code2;

    @FXML
    private TextField tfPass_code3;

    @FXML
    private TextField tfPass_code4;

    @FXML
    private TextField tfRegister_github;

    @FXML
    private TextField tfRegister_mail;

    @FXML
    private TextField tfRegister_name;

    @FXML
    private TextField tfRegister_pass;

    @FXML
    private TextField tfRegister_phone;

    @FXML
    private TextField tfRegister_username;

    @FXML
    private VBox vboxInfor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stackPane.getChildren().forEach(pane -> pane.setVisible(false));
        paneLogin.setVisible(true);
        initButton();
    }

    public void initButton() {
        setButtonAction(buttonLogin_signUp, paneRegister);
        setButtonAction(buttonRegister_next, paneConfirmAcc);
        setButtonAction(buttonLogin_ForgotPass, paneForgotPass);
        setButtonAction(buttonForgot_go, paneConfirmPass);
        setButtonAction(buttonRegister_back, paneLogin);
        setButtonAction(buttonAcc_back, paneRegister);
        setButtonAction(buttonForgot_back, paneLogin);
    }

    public void setButtonAction(Button button,BorderPane pane1) {
        button.setOnAction(e -> {
            stackPane.getChildren().forEach(pane -> pane.setVisible(false));
            pane1.setVisible(true);
        });
    }
}
