package com.dga.equiz.controller.editProfile;

import com.dga.equiz.controller.ProfileContainerController;
import com.dga.equiz.model.Profile;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.*;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class EditInforController implements Initializable {

    @FXML
    public Circle circle;

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
    public TextField date;

    public List<EventHandler<ActionEvent>> onCompleteSave = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Profile profile = ApplicationData.getInstance().profile;
        try {
            changeInfor(profile.getID());
            if (profile.getLinkAva() != null) {
                circle.setFill(new ImagePattern(EquizUtils.toImage(profile.getID())));
            }
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
        if (dobData != null) date.setText(dobData);
        if (phoneData != null) { tfPhone.setText(phoneData); }
        if (githubData != null) {
            String[] tmp = profile.getGithub().split("/");
            tfGithub.setText(tmp[tmp.length - 1]);
        }

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

        LocalDate myDate = LocalDate.parse(date.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String query = "UPDATE `dga_data`.`information`";
        query += "SET " + "`name`" + " = '" + tfName.getText() + "' ,";
        query += "`mail`" + " = '" + tfMail.getText() + "' ,";
        query += "`phone`" + " = '" + tfPhone.getText() + "' ,";
        query += "`github`" + " = '" + tfGithub.getText() + "' ,";
        query += "`dob`" + " = '" + myFormattedDate + "' ";
        query += "WHERE (`id` = '" + id + "')";

        DBHelper.executeUpdate(query);

        Profile profile = ApplicationData.getInstance().profile;
        profile.setName(tfName.getText());
        profile.setMail(tfMail.getText());
        profile.setPhone(tfPhone.getText());
        profile.setGithub(tfGithub.getText());
        profile.setDob(myFormattedDate);
    }

    public void changeImage(ActionEvent event) throws SQLException, IOException {
        Profile profile = ApplicationData.getInstance().profile;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            String link = file.getAbsolutePath();
            try (Connection connection = DriverManager.getConnection(DBHelper.MysqlURL, SecretKey.USERNAME, SecretKey.PASSWORD)) {
                File imageFile = new File(link);
                FileInputStream fis = new FileInputStream(imageFile);

                String updateSql = "UPDATE information SET link_ava_test = ? WHERE id = '" + profile.getID() + "';";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setBinaryStream(1, fis, (int) imageFile.length());
                    updateStatement.executeUpdate();
                    System.out.println("Image updated successfully.");
                }

                fis.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }

            // Convert FileInputStream to byte[]
            byte[] imageData = convertFileInputStreamToByteArray(file);

            // Set the byte[] to the profile
            profile.setLinkAva(imageData);

            Image newImage = new Image(file.toURI().toString());
            circle.setFill(new ImagePattern(newImage));
        }
    }

    // Helper method to convert FileInputStream to byte[]
    public static byte[] convertFileInputStreamToByteArray(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        fis.close();
        bos.close();

        return bos.toByteArray();
    }
}