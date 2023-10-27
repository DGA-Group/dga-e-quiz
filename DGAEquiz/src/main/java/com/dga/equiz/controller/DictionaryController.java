package com.dga.equiz.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.model.word.Definition;
import com.dga.equiz.model.word.Meaning;
import com.dga.equiz.model.word.Phonetic;
import com.dga.equiz.model.word.Word;
import com.dga.equiz.utils.EquizUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.kordamp.ikonli.javafx.FontIcon;

public class DictionaryController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField searchingField;

    @FXML
    private Label labelMeaning;

    @FXML
    private Label labelPhonetic;

    @FXML
    private Button buttonSearch;
    @FXML
    private VBox wordsBox;

    private List<String> suggesstions = new ArrayList<String>();

    public void onStartup() throws IOException {
        AutoCompletionBinding<String> autoCompletionBinding = TextFields.bindAutoCompletion(searchingField, suggesstions);
        autoCompletionBinding.setPrefWidth(searchingField.getPrefWidth());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            String selectedWord = event.getCompletion();
        });
        searchingField.textProperty().addListener((obs, oldText, newText) -> {
            TextFields.bindAutoCompletion(searchingField, "");
            suggesstions.clear();
            suggesstions.addAll(getYourSuggestedWords());
            TextFields.bindAutoCompletion(searchingField, suggesstions);
        });
    }

    private List<String> getYourSuggestedWords() {
        try {
            List<String> suggestedWords = new ArrayList<>();
            List<Word> inputSuggestWord = EquizUtils.FetchSuggestWordFromDictionary(searchingField.getText());
            for (var word : inputSuggestWord) {
                suggestedWords.add(word.getWord());
            }
            return suggestedWords;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể tải danh sách từ khóa gợi ý.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            onStartup();
            onEnterSearch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onEnterSearch() {
        searchingField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    try {
                        onClickSearch();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void onClickSearch() throws IOException {
        wordsBox.getChildren().clear();
        String inputWord = searchingField.getText();

        List<Word> resWord = EquizUtils.FetchWordFromDictionary(inputWord);
        for (var wor : resWord) {
            NodeObject wordView = EquizUtils.Instantiate("/view/WordView.fxml");
            wordsBox.getChildren().add(wordView.getNode());
            WordController controller = wordView.getController();
            List<Meaning> meanings = wor.getMeanings();
            List<Phonetic> phonetics = wor.getPhonetics();
            String pathOfSpeech = "";
            String pathOfAudio = "";
            for (var mean : meanings) {
                if (!mean.getPartOfSpeech().isEmpty()) {
                    pathOfSpeech += mean.getPartOfSpeech();
                    break;
                }
            }
            for (var phone : phonetics) {
                if (!phone.getAudio().isEmpty()) {
                    pathOfAudio += phone.getAudio();
                    break;
                }
            }
            if (pathOfSpeech.isEmpty() || pathOfAudio.isEmpty()) {
                return;
            }
            controller.setupWordView(wor, wor.getWord(), pathOfSpeech, wor.getPhonetic(), pathOfAudio);
        }


    }

}

