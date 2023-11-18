package com.dga.equiz.controller.editProfile;

import com.dga.equiz.controller.ProfileContainerController;
import com.dga.equiz.model.Profile;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.ControllerManager;
import com.dga.equiz.utils.DBHelper;
import com.dga.equiz.utils.EquizUtils;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class EditInforController implements Initializable {

    @FXML
    public Button buttonSave;

    @FXML
    public Button buttonBack;

    @FXML
    public TextField tfName;

    @FXML
    public TextField tfMail;

    @FXML
    public TextField tfPhone;

    @FXML
    public TextField tfGithub;

    @FXML
    public DatePicker date;

    public List<EventHandler<ActionEvent>> onCompleteSave = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ControllerManager.getInstance().editInforController = this;
        Profile profile = ApplicationData.getInstance().profile;
        try {
            changeInfor(profile.getID());
        } catch (SQLException e) {
        }
    }

    public void changeInfor(int id) throws SQLException {
        Profile profile = ApplicationData.getInstance().profile;
        String nameData = profile.getName();
        String mailData = profile.getMail();
        String dobData = profile.getDob();
        String phoneData = profile.getPhone();
        String githubData = profile.getGithub();

        if (nameData != null) { tfName.setText(nameData); }
        if (mailData != null) { tfMail.setText(mailData); }
        if (dobData != null) date.setValue(LocalDate.parse(dobData));
        if (phoneData != null) { tfPhone.setText(phoneData); }
        if (githubData != null) { tfGithub.setText(githubData); }

        buttonSave.setOnAction((ActionEvent e) -> {
            try {
                changeDatabase(id);
                for(var event : onCompleteSave){
                    event.handle(e);
                }
            } catch (SQLException ex) {
            }
        });
    }

    public void changeDatabase(int id) throws SQLException {

        LocalDate myDate = date.getValue();
        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String query = "UPDATE `dga_data`.`information`";
        query += "SET " + "`name`" + " = '" + tfName.getText() + "' ,";
        query += "`mail`" + " = '" + tfMail.getText() + "' ,";
        query += "`phone`" + " = '" + tfPhone.getText() + "' ,";
        query += "`github`" + " = '" + tfGithub.getText() + "' ,";
        query += "`dob`" + " = '" + myFormattedDate + "' ";
        query += "WHERE (`id` = '" + String.valueOf(id) + "')";

        DBHelper.executeUpdate(query);

        Profile profile = ApplicationData.getInstance().profile;
        profile.setName(tfName.getText());
        profile.setMail(tfMail.getText());
        profile.setPhone(tfPhone.getText());
        profile.setGithub(tfGithub.getText());
        profile.setDob(myFormattedDate);
    }
}