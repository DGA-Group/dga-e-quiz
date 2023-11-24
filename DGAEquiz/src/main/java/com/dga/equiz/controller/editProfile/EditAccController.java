package com.dga.equiz.controller.editProfile;

import com.dga.equiz.model.Profile;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.ControllerManager;
import com.dga.equiz.utils.DBHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.dga.equiz.utils.EquizUtils.showAlert;

public class EditAccController implements Initializable {

    @FXML
    public Button buttonBack;

    @FXML
    private Button button_check;

    @FXML
    private Button button_save;

    @FXML
    private Label labelConfirmPass;

    @FXML
    private Label labelNewPass;

    @FXML
    private PasswordField passFConfirm;

    @FXML
    private PasswordField passFCurrent;

    @FXML
    private PasswordField passFNew;

    public List<EventHandler<ActionEvent>> onCompleteSave = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passFConfirm.setVisible(false);
        passFNew.setVisible(false);
        labelNewPass.setVisible(false);
        labelConfirmPass.setVisible(false);
        Profile profile = ApplicationData.getInstance().profile;
        initbutton(profile.getID());
    }

    public void initbutton(int id) {
        button_check.setOnAction((ActionEvent e) -> {
            if (passFCurrent.getText().isEmpty()) {
                showAlert("Please enter your current password \n " +
                        "If you forgot your password please log out and click \"Forgot Password\"");
                return;
            }

            String current = passFCurrent.getText();
            String query = "SELECT password\n" +
                    "FROM information\n" +
                    "WHERE id = " + id + ";";

            ResultSet resultSet = null;
            Statement statement = null;
            Connection connection = null;

            String passOutput = "";
            try {
                resultSet = DBHelper.executeQuery(query);
                statement = resultSet.getStatement();
                connection = statement.getConnection();
                while(resultSet.next()) {
                    passOutput = resultSet.getString(1);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }  finally {
                try {
                    DBHelper.closeQuery(resultSet, statement, connection);
                }catch (Exception ignore) {}
            }

            if (passOutput.equals(passFCurrent.getText())) {
                passFConfirm.setVisible(true);
                passFNew.setVisible(true);
                labelNewPass.setVisible(true);
                labelConfirmPass.setVisible(true);
            } else {
                labelNewPass.setVisible(false);
                labelConfirmPass.setVisible(false);
                showAlert("Wrong Password");
                return;
            }
        });

        button_save.setOnAction((ActionEvent e) -> {
            boolean flag = false;
            if (passFNew.getText().isEmpty() || passFConfirm.getText().isEmpty()) {
                showAlert("Please write your password");
                return;
            } else if (passFNew.getText().equals(passFConfirm.getText())) {
                flag = true;
            } else {
                showAlert("Confirm Pass Is Wrong");
                return;
            }

            if (flag) {
                String query = "UPDATE information\n" +
                        "SET password = '" + passFConfirm.getText() + "'\n" +
                        "WHERE id = " + id + ";";
                try {
                    DBHelper.executeUpdate(query);
                    for(var event : onCompleteSave){
                        event.handle(e);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            Profile profile = ApplicationData.getInstance().profile;
            profile.setPassword(passFConfirm.getText());

            passFConfirm.setText(null);
            passFNew.setText(null);
            passFCurrent.setText(null);
            passFNew.setVisible(false);
            passFConfirm.setVisible(false);
        });
    }
}
