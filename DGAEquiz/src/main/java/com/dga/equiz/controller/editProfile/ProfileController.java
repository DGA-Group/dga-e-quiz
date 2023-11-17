package com.dga.equiz.controller.editProfile;

import com.dga.equiz.model.Profile;
import com.dga.equiz.utils.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Circle circle;
    @FXML
    public Button buttonEdit;

    @FXML
    public Label labelName;

    @FXML
    public Label labelMail;

    @FXML
    public Label labelDOB;

    @FXML
    public Label labelPhone;

    @FXML
    public Label labelGithub;

    @FXML
    public Label labelID;

    @FXML
    public Button logOutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Profile profile = ApplicationData.getInstance().profile;
        try {
            setLabel(profile.getID());
        } catch (SQLException e) {

        }

        String sqlQuery = "SELECT * FROM `information` WHERE id = '" + String.valueOf(profile.getID()) + "';";
        try {
            ResultSet resultSet = DBHelper.executeQuery(sqlQuery);
            if (resultSet.next()) {
                byte[] imageData = resultSet.getBytes("link_ava_test");
                if (imageData != null) {
                    Image image = new Image(new ByteArrayInputStream(imageData));
                    circle.setFill(new ImagePattern(image));
                }
            }
        } catch (SQLException e) {

        }

        logOutButton.setOnAction((ActionEvent e) -> {
            StageManager.getInstance().myApplicationStage.hide();
            StageManager.getInstance().loginStage.show();

        });
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

    public void setLabel(int id) throws SQLException {

        Profile profile = ApplicationData.getInstance().profile;

        labelName.setText(profile.getName());
        labelMail.setText(profile.getMail());
        labelDOB.setText(profile.getDob());
        labelPhone.setText(profile.getPhone());
        labelGithub.setText(profile.getGithub());
        labelID.setText(String.valueOf(profile.getID()));

    }
}
