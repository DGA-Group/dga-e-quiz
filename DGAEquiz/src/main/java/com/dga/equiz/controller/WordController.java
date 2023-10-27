package com.dga.equiz.controller;

import com.dga.equiz.model.word.Definition;
import com.dga.equiz.model.word.Meaning;
import com.dga.equiz.model.word.Phonetic;
import com.dga.equiz.model.word.Word;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.kordamp.ikonli.javafx.FontIcon;

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

    public void setupWordView(Word word, String textOfWord, String partOfSpeech, String textOfPhonetic, String soundPath) {
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
            Label definitionsLabel = new Label();
            vBox.getChildren().add(definitionsLabel);
            definitionsLabel.setWrapText(true);
            definitionsLabel.setText("Definition:  " + def.getDefinition());
            if (def.getExample() == null) {
                cntDef++;
            } else {
                Label exampleLabel = new Label();
                vBox.getChildren().add(exampleLabel);
                exampleLabel.setWrapText(true);
                exampleLabel.setText("Example:  " + def.getExample());
                cntEx++;
                cntDef++;
            }
            if (cntEx == 4 || cntDef == 4) {
                break;
            }
        }
        Label synonymLabel = new Label();
        Label antonymLabel = new Label();
        vBox.getChildren().add(synonymLabel);
        vBox.getChildren().add(antonymLabel);
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

        synonymLabel.setText("Synonyms : " + synonyms);
        synonymLabel.setWrapText(true);
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

        antonymLabel.setText("Antonyms : "+ antonyms);
        antonymLabel.setWrapText(true);
    }
}


