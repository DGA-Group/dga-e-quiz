package com.dga.equiz.controller;

import com.dga.equiz.model.Profile;
import com.dga.equiz.model.word.Definition;
import com.dga.equiz.model.word.Word;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.DBHelper;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class WordController {
    @FXML
    private Label labelWord;
    @FXML
    private Label labelPartOfSpeech;
    @FXML
    private Label labelTextOfPhonetic;
    @FXML
    private Button buttonPlaySound;
    @FXML
    private VBox vBox;
    private Word currentWord;

    public void setupWordView(Word word, String textOfWord, String partOfSpeech, String textOfPhonetic, String soundPath) {
        this.currentWord = word;
        this.labelWord.setText(textOfWord);
        this.labelPartOfSpeech.setText(partOfSpeech);
        this.labelTextOfPhonetic.setText(textOfPhonetic);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        Media sound;
        sound = new Media(soundPath);
        mediaPlayer[0] = new MediaPlayer(sound);
        buttonPlaySound.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                mediaPlayer[0].stop();
                mediaPlayer[0] = new MediaPlayer(sound);
                mediaPlayer[0].play();

            }
        });
        int i = 0;
        while (word.getMeanings().get(i).getDefinitions().isEmpty()) {
            i++;
        }
        List<Definition> definitions = word.getMeanings().get(i).getDefinitions();
        int cntEx = 0;
        int cntDef = 0;
        for (var def : definitions) {
            TextFlow definitionsLabel = aLabel(720, "Definition: " + def.getDefinition());
            vBox.getChildren().add(definitionsLabel);
            if (def.getExample() == null) {
                cntDef++;
            } else {
                TextFlow exampleLabel = aLabel(720, "Example: " + def.getExample());
                vBox.getChildren().add(exampleLabel);
                cntEx++;
                cntDef++;
            }
            if (cntEx == 4 || cntDef == 4) {
                break;
            }
        }
        String synonyms = "";
        for (int k = 0; k < word.getMeanings().size(); k++) {
            if (!word.getMeanings().get(k).getSynonyms().isEmpty()) {
                int sideOfSynonyms = word.getMeanings().get(k).getSynonyms().size();
                for (int j = 0; j < sideOfSynonyms; j++) {
                    synonyms += word.getMeanings().get(k).getSynonyms().get(j) + ",";
                }
            }
        }
        if (synonyms.length() > 0) {
            synonyms = synonyms.substring(0, synonyms.length() - 1) + '.';
        }
        TextFlow synonymLabel = aLabel(720, "Synonyms: " + synonyms);
        vBox.getChildren().add(synonymLabel);
        String antonyms = "";
        for (int k = 0; k < word.getMeanings().size(); k++) {
            if (!word.getMeanings().get(k).getAntonyms().isEmpty()) {
                int sideOfSynonyms = word.getMeanings().get(k).getAntonyms().size();
                for (int j = 0; j < sideOfSynonyms; j++) {
                    antonyms += word.getMeanings().get(k).getAntonyms().get(j) + ",";
                }
            }
        }
        if (antonyms.length() > 1) {
            antonyms = antonyms.substring(0, antonyms.length() - 1) + '.';
        }
        TextFlow antonymLabel = aLabel(720, "Antonyms: " + antonyms);
        vBox.getChildren().add(antonymLabel);
    }

    public String getMeaning(Word word) {
        int i = 0;
        while (word.getMeanings().get(i).getDefinitions().isEmpty()) {
            i++;
        }
        return word.getMeanings().get(i).getDefinitions().get(0).getDefinition();
    }

    public void onClickSave() throws SQLException {
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        String savedOnlineWord = labelWord.getText();
        String savedOnlineMeaning = getMeaning(currentWord);
        Profile profile = ApplicationData.getInstance().profile;
        int profileID = profile.getID();
        String query = "SELECT * FROM flashcard WHERE id = '" + profileID
                + "' AND word = '" + savedOnlineWord + "' AND meaning = '" + savedOnlineMeaning + "';";
        resultSet = DBHelper.executeQuery(query);
        statement = resultSet.getStatement();
        connection = statement.getConnection();
        if (resultSet.next()) {
            EquizUtils.showAlert("Already Exist !");
        } else {
            String updateQuery = "INSERT INTO flashcard (id, word, meaning) VALUES ("
                    + profileID + ",'" + savedOnlineWord + "','" + savedOnlineMeaning + "');";
            try {
                DBHelper.executeUpdate(updateQuery);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBHelper.closeQuery(resultSet, statement, connection);
            }
        }
    }

    private TextFlow aLabel(int maxWidth, String... pString) {
        TextFlow textFlowLabel = new TextFlow();
        textFlowLabel.setMaxWidth(maxWidth);
        textFlowLabel.setPrefWidth(maxWidth);
        textFlowLabel.setTextAlignment(TextAlignment.LEFT);
        for (String aString : pString) {
            Text text = new Text(aString);
            text.setFill(Color.WHITE);
            textFlowLabel.getChildren().add(text);
        }
        textFlowLabel.setStyle("-fx-text-fill: #FFFFFF;-fx-font-size: 18");
        return textFlowLabel;
    }
}


