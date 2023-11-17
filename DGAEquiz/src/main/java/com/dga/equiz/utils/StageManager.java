package com.dga.equiz.utils;

import javafx.stage.Stage;

public class StageManager {
    private static StageManager instance;
    public Stage offlineDeleteDictionaryStage;
    public Stage changeWordStage;

    private StageManager() {

    }

    public static StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }

    public Stage loginStage;
    public Stage myApplicationStage;
    public Stage offlineAddDictionaryStage;
}
