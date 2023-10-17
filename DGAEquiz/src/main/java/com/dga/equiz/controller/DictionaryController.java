package com.dga.equiz.controller;
import  java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.dga.equiz.model.word.Definition;
import com.dga.equiz.model.word.Meaning;
import com.dga.equiz.model.word.Phonetic;
import com.dga.equiz.model.word.Word;
import com.dga.equiz.utils.EquizUtils;
import  javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;
public class DictionaryController implements Initializable {
    @FXML
    private  AnchorPane root;
    @FXML
    private  TextField words;

    @FXML
    private Label labelMeaning;

    @FXML
    private Label labelPhonetic;

    @FXML
    private Button buttonSearch;
    @FXML
    private VBox vBox;
    public  void onStartup() throws IOException{
        TextFields.bindAutoCompletion(words,"hihaha"
                );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            onStartup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickSearch(){
        String inputWord = words.getText();
        try {
            List<Word> resWord = EquizUtils.FetchWordFromDictionary(inputWord);
            for(var wor : resWord) {
                Label phoneticLabel = new Label();
                Label wordLabel = new Label();
                vBox.getChildren().add(wordLabel);
                vBox.getChildren().add(phoneticLabel);
                wordLabel.setText(wor.getWord() + ":" + "\n");
                phoneticLabel.setText(wor.getPhonetic() + "\t");
                List<Phonetic> phonetics = wor.getPhonetics();
                List<Meaning> meanings = wor.getMeanings();
                for (var phoneText : phonetics){
                    Label textLabel = new Label();
                    vBox.getChildren().add(textLabel);
                    textLabel.setText("text   "+ phoneText.getText());
                }
                for (var mean : meanings) {
                    Label meaningLabel = new Label();
                    Label partOfspeechLabel = new Label();
                    vBox.getChildren().add(meaningLabel);
                    vBox.getChildren().add(partOfspeechLabel);
                    meaningLabel.setText("meanings : [  ");
                    partOfspeechLabel.setText("Part of speech:  " + mean.getPartOfSpeech());
                    List<Definition> definitions = mean.getDefinitions();
                    for (var def : definitions){
                        Label definitionsLabel = new Label();
                        Label synonymLabel = new Label();
                        Label antonymLabel = new Label();
                        Label exampleLabel = new Label();
                        vBox.getChildren().add(definitionsLabel);
                        vBox.getChildren().add(synonymLabel);
                        vBox.getChildren().add(antonymLabel);
                        vBox.getChildren().add(exampleLabel);
                        definitionsLabel.setText("Definition:  " + def.getDefinition());
                        synonymLabel.setText("Synonym:  "+ def.getSynonyms());
                        antonymLabel.setText("Antonym:  " + def.getAntonyms());
                        exampleLabel.setText("Example:  " + def.getExample());
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }





    }

}

