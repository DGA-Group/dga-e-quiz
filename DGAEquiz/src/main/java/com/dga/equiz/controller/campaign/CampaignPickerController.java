package com.dga.equiz.controller.campaign;

import com.dga.equiz.model.Lesson;
import com.dga.equiz.model.Revision;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class CampaignPickerController implements Initializable {

    //region FXML Reference
    @FXML
    public Button buttonLearn;
    @FXML
    public Button buttonRevise;
    //endregion

    private Lesson lesson;
    private Revision revision;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }

}
