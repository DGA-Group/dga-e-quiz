package com.dga.equiz.controller;

import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.model.word.Meaning;
import com.dga.equiz.model.word.Phonetic;
import com.dga.equiz.model.word.Word;
import com.dga.equiz.utils.EquizUtils;
import javafx.application.Platform;
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
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

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

    private CopyOnWriteArrayList<String> suggesstions = new CopyOnWriteArrayList<>();

    private CompletableFuture<List<Word>> currentSuggestionTask = CompletableFuture.completedFuture(new ArrayList<>());

    public void onStartup() {
        TextFields.bindAutoCompletion(searchingField, suggesstions);
        searchingField.textProperty().addListener((obs, oldText, newText) -> {
            currentSuggestionTask.cancel(true); // Hủy luồng trước khi bắt đầu một luồng mới
            currentSuggestionTask = CompletableFuture.supplyAsync(() -> {
                try {
                    return EquizUtils.FetchSuggestWordFromDictionary(newText);
                } catch (IOException e) {
                    throw new RuntimeException("Không thể tải danh sách từ khóa gợi ý.", e);
                }
            }).exceptionally(ex -> {
                ex.printStackTrace();
                return new ArrayList<>(); // Trả về danh sách trống nếu có lỗi
            });
            currentSuggestionTask.thenAcceptAsync(suggestedWords -> Platform.runLater(() -> {
                suggesstions.clear();
                for (Word word : suggestedWords) {
                    suggesstions.add(word.getWord());
                }
                TextFields.bindAutoCompletion(searchingField, suggesstions);
            }));
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onStartup();
        onEnterSearch();
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
        String inputWord = searchingField.getText().trim();
        CompletableFuture.supplyAsync(() -> {
            try {
                return EquizUtils.FetchWordFromDictionary(inputWord);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).thenAcceptAsync(words -> Platform.runLater(() -> {
            try {
                displayWords(words);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }))
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return null;
                });
    }

    private void displayWords(List<Word> resWord) throws IOException {
        wordsBox.getChildren().clear();
        for (var wor : resWord) {
            NodeObject wordView = EquizUtils.Instantiate("/view/WordView.fxml");
            wordsBox.getChildren().add(wordView.getNode());
            WordController controller = wordView.getController();
            List<Meaning> meanings = wor.getMeanings();
            List<Phonetic> phonetics = wor.getPhonetics();
            String pathOfSpeech = "";
            String pathOfAudio = "";
            for (Meaning mean : meanings) {
                if (!mean.getPartOfSpeech().isEmpty()) {
                    pathOfSpeech += mean.getPartOfSpeech();
                    break;
                }
            }
            for (Phonetic phone : phonetics) {
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

