package com.dga.equiz.controller.login;

import com.dga.equiz.Main;
import com.dga.equiz.model.Mailer;
import com.dga.equiz.model.Profile;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import static com.dga.equiz.utils.EquizUtils.showAlert;

public class LoginController implements Initializable {

    @FXML
    private Label labelRegister_atlert;

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
    private TextField tfAcc_confirmPassShow;

    @FXML
    private PasswordField pfAcc_confirmPass;

    @FXML
    private TextField tfForgot_Npass;

    @FXML
    private TextField tfForgot_confNpass;

    @FXML
    private TextField tfForgot_mail;

    @FXML
    private TextField tfForgot_username;

    @FXML
    private PasswordField pfLogin_password;

    @FXML
    private TextField tfLogin_showPass;

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

    public List<EventHandler<ActionEvent>> onCompleteSave = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stackPane.getChildren().forEach(pane -> pane.setVisible(false));
        paneLogin.setVisible(true);
        initlabel();
        initButton();
    }

    public void initButton() {
        // login
        buttonLogin_login.setOnAction((ActionEvent e) -> {

            if (tfLogin_username.getText().isEmpty() && pfLogin_password.getText().isEmpty()) {
                showAlert("Please enter your username and password");
                return;
            } else if (tfLogin_username.getText().isEmpty()) {
                showAlert("Please enter your username");
                return;
            } else if (pfLogin_password.getText().isEmpty()) {
                showAlert("Please enter your password");
                return;
            }

            String userInput = tfLogin_username.getText();
            String query = "SELECT password FROM information WHERE username = '" + userInput + "';";
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
            }

            if (passOutput.equals(pfLogin_password.getText()) || passOutput.equals(tfLogin_showPass.getText())) {
//                paneLogin.setVisible(false);
                try {
                    runMain();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                labelLogin_WrongPass.setVisible(true);
            }
        });

        checkLogin_pass.setOnAction((ActionEvent e) -> {
            showPassword(checkLogin_pass,pfLogin_password,tfLogin_showPass);
        });

        // sign up
        final int[] code = {0};
        buttonRegister_next.setOnAction((ActionEvent e) -> {
            stackPane.getChildren().forEach(pane -> pane.setVisible(false));
            paneConfirmAcc.setVisible(true);
            Random random = new Random();
            code[0] = 1000 + random.nextInt(9000);
            String message = "Ma Code cua ban la: " + code[0];
            Mailer.send("tuankoi921@gmail.com", "uvyt ehsf ufew uyru",tfRegister_mail.getText(), "Confirm Acc DGAEQuiz", message);
        });

        // Confirm Acc
        checkAcc_showAcc.setOnAction((ActionEvent e) -> {
            showPassword(checkAcc_showAcc, pfAcc_confirmPass, tfAcc_confirmPassShow);
        });

        buttonAcc_go.setOnAction((ActionEvent e) -> {
            String nb1 = tfAcc_code1.getText();
            String nb2 = tfAcc_code2.getText();
            String nb3 = tfAcc_code3.getText();
            String nb4 = tfAcc_code4.getText();
            int codeInput = 0;
            try {
                codeInput = Integer.parseInt(nb1) * 1000 + Integer.parseInt(nb2) * 100 + Integer.parseInt(nb3) * 10 + Integer.parseInt(nb4);
            } catch (NumberFormatException err) {
                showAlert("Chuỗi không thể chuyển đổi thành số nguyên.");
                return;
            }

            if (codeInput != code[0] || !tfAcc_confirmPassShow.getText().equals(tfRegister_pass.getText())) {
                showAlert("Confirm Code hoặc Confirm Password của bạn không đúng");
                return;
            } else
            {
                try {
                    insertInfor();
                    for(var event : onCompleteSave){
                        event.handle(e);
                    }
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Forgot Password
        final int[] codeForgot = {0};
        buttonForgot_go.setOnAction((ActionEvent e) -> {
            if (tfForgot_username.getText().isEmpty() || tfForgot_Npass.getText().isEmpty() ||
                    tfForgot_mail.getText().isEmpty() || tfForgot_confNpass.getText().isEmpty()) {
                showAlert("Vui lòng điền đầy đủ thông tin");
                return;
            } else
            {
                if (!tfForgot_Npass.getText().equals(tfForgot_confNpass.getText())) {
                    showAlert("Vui lòng nhập lại mật khẩu");
                    return;
                } else {
                    stackPane.getChildren().forEach(pane -> pane.setVisible(false));
                    paneConfirmPass.setVisible(true);
                    Random random = new Random();
                    codeForgot[0] = 1000 + random.nextInt(9000);
                    String message = "Ma Code cua ban la: " + codeForgot[0];
                    Mailer.send("tuankoi921@gmail.com", "uvyt ehsf ufew uyru",tfForgot_mail.getText(), "Confirm Acc DGAEQuiz", message);
                }
            }
        });

        // Pass
        buttonPass_go.setOnAction((ActionEvent e) -> {
            String nb1 = tfPass_code1.getText();
            String nb2 = tfPass_code2.getText();
            String nb3 = tfPass_code3.getText();
            String nb4 = tfPass_code4.getText();
            int codeInput = 0;
            try {
                codeInput = Integer.parseInt(nb1) * 1000 + Integer.parseInt(nb2) * 100 + Integer.parseInt(nb3) * 10 + Integer.parseInt(nb4);
            } catch (NumberFormatException err) {
                showAlert("Chuỗi không thể chuyển đổi thành số nguyên.");
                return;
            }

            if (codeInput != codeForgot[0]) {
                showAlert("Confirm Code của bạn không đúng");
                return;
            } else if (!checkAcc_agree.isSelected()) {
                showAlert("Please agree with all terms to use this app");
            } else {
                try {
                    runMain();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                String query = "UPDATE information\n" +
                        "SET password = '" + tfForgot_Npass.getText() + "' WHERE username = '" + tfForgot_username.getText() + "';";
                try {
                    DBHelper.executeUpdate(query);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // general
        setButtonAction(buttonLogin_signUp, paneRegister);
        setButtonAction(buttonLogin_ForgotPass, paneForgotPass);
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

    public void insertInfor() throws SQLException, IOException {

        if (!tfRegister_username.getText().isEmpty() && !tfRegister_pass.getText().isEmpty()) {

            String myFormattedDate = "0001-01-01";
            if (dateRegister_dob.getValue() != null) {
                LocalDate myDate = dateRegister_dob.getValue();
                myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

            try (Connection connection = DriverManager.getConnection(DBHelper.MysqlURL, SecretKey.USERNAME, SecretKey.PASSWORD)) {
                File imageFile = new File("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\test.jpg");
                FileInputStream fis = new FileInputStream(imageFile);

                String query = "INSERT INTO information (name, mail, dob, phone, github, username, password, link_ava_test) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    // Set other parameters
                    preparedStatement.setString(1, tfRegister_name.getText());
                    preparedStatement.setString(2, tfRegister_mail.getText());
                    preparedStatement.setString(3, myFormattedDate);
                    preparedStatement.setString(4, tfRegister_phone.getText());
                    preparedStatement.setString(5, tfRegister_github.getText());
                    preparedStatement.setString(6, tfRegister_username.getText());
                    preparedStatement.setString(7, tfRegister_pass.getText());

                    preparedStatement.setBinaryStream(8, fis, (int) imageFile.length());

                    preparedStatement.executeUpdate();
                    System.out.println("Data inserted successfully.");
                }

                fis.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }

            stackPane.getChildren().forEach(pane -> pane.setVisible(false));
            paneConfirmAcc.setVisible(true);
            runMain();
        } else {
            labelRegister_atlert.setVisible(true);
        }

    }

    public void initlabel() {
        labelRegister_atlert.setVisible(false);
        labelLogin_WrongPass.setVisible(false);
        tfLogin_showPass.setVisible(false);
        tfAcc_confirmPassShow.setVisible(false);
    }

    private void showPassword(CheckBox checkbox, PasswordField pf, TextField tf) {
        if (checkbox.isSelected()) {
            tf.setText(pf.getText());
            pf.setVisible(false);
            tf.setVisible(true);
        } else {
            pf.setText(tf.getText());
            pf.setVisible(true);
            tf.setVisible(false);
        }
    }

    private void addStyle(Scene scene, String cssPath) {
        scene.getStylesheets().add(String.valueOf(Main.class.getResource(cssPath)));
    }

    private void runMain() throws SQLException, IOException {
        String sqlQuery = "SELECT * FROM `information` WHERE username = '" + tfLogin_username.getText() + "';";
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;

        resultSet = DBHelper.executeQuery(sqlQuery);
        statement = resultSet.getStatement();
        connection = statement.getConnection();

        Profile profile = ApplicationData.getInstance().profile;

        if (resultSet.next()) {
            profile.setID(resultSet.getInt(1));
            profile.setName(resultSet.getString(2));
            profile.setMail(resultSet.getString(3));
            profile.setDob(resultSet.getString(4));
            profile.setPhone(resultSet.getString(5));
            profile.setGithub(resultSet.getString(6));
            profile.setUsername(resultSet.getString(7));
            profile.setPassword(resultSet.getString(8));
            profile.setLinkAva(resultSet.getBytes(9));
        }


        NodeObject applicationView = EquizUtils.Instantiate("/view/MyApplication.fxml");
        Scene myApplicationScene = new Scene((Parent) applicationView.getNode(), 854, 480, Color.TRANSPARENT);
        Stage myApplicationStage = StageManager.getInstance().myApplicationStage = new Stage();
        addStyle(myApplicationScene, "/css/learnDesign.css");
        myApplicationStage.initStyle(StageStyle.TRANSPARENT);
        myApplicationStage.setScene(myApplicationScene);
        myApplicationStage.hide();

        StageManager.getInstance().myApplicationStage.show();
        StageManager.getInstance().loginStage.hide();
    }
}
