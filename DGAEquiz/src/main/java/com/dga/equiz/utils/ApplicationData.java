package com.dga.equiz.utils;

import com.dga.equiz.model.Campaign;
import com.dga.equiz.model.Lesson;
import com.dga.equiz.model.question.FillQuestion;
import com.dga.equiz.model.question.ImageQuestion;
import com.dga.equiz.model.question.ListeningQuestion;
import com.dga.equiz.model.question.TranslateQuestion;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ApplicationData {

    private static ApplicationData instance;

    private ApplicationData() {

    }

    public static ApplicationData getInstance() {
        if (instance == null) {
            instance = new ApplicationData();
        }
        return instance;
    }

    private Map<Long, Campaign> campaignData = new HashMap<Long, Campaign>();
    private Map<Long, Lesson> lessonData = new HashMap<Long, Lesson>();
    private Map<Long, ImageQuestion> imageQuestionData = new HashMap<Long, ImageQuestion>();
    private Map<Long, ListeningQuestion> listeningQuestionData = new HashMap<Long, ListeningQuestion>();
    private Map<Long, FillQuestion> fillQuestionData = new HashMap<Long, FillQuestion>();


    private Map<Long, TranslateQuestion> translateQuestionData = new HashMap<Long, TranslateQuestion>();

    public Map<Long, Campaign> getCampaignData() {
        return campaignData;
    }

    public Map<Long, Lesson> getLessonData() {
        return lessonData;
    }

    public Map<Long, ImageQuestion> getImageQuestionData() {
        return imageQuestionData;
    }

    public Map<Long, ListeningQuestion> getListeningQuestionData() {
        return listeningQuestionData;
    }

    public Map<Long, FillQuestion> getFillQuestionData() {
        return fillQuestionData;
    }

    public Map<Long, TranslateQuestion> getTranslateQuestionData() {
        return translateQuestionData;
    }

    public void loadAllData() {
        loadAllCampaign();
        loadAllLesson();
        loadAllImageQuestion();
        loadAllListeningQuestion();
        loadAllFillQuestion();
        loadAllTranslateQuestion();
    }

    public void loadAllCampaign() {
        String sqlQuery = "SELECT * FROM `campaign`";
        try {
            ResultSet resultSet = DBHelper.query(sqlQuery);
            while (resultSet.next()) {
                long campaignNumber = resultSet.getLong(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                Campaign newCampaign = new Campaign(campaignNumber, title, description);
                campaignData.put(campaignNumber, newCampaign);
            }
        } catch (Exception e) {
            System.out.println("===========================");
            System.out.println("Unable to load all campaign");
            System.out.println("===========================");
            e.printStackTrace();
        }

    }

    public void loadAllLesson() {

    }

    public void loadAllImageQuestion() {
        String sqlQuery = "SELECT * FROM `image_question`";
        try {
            ResultSet resultSet = DBHelper.query(sqlQuery);
            while (resultSet.next()) {
                long questionId = resultSet.getLong(1);
                String question = resultSet.getString(2);
                String imageSrc = resultSet.getString(3);
                String[] options = new String[5];
                for (int i = 1, k = 4; k <= 7; i++, k++) {
                    options[i] = resultSet.getString(k);
                }
                byte correctAnswer = resultSet.getByte(8);
                ImageQuestion newImageQuestion = new ImageQuestion(questionId, question, imageSrc, options[1], options[2]
                        , options[3], options[4], correctAnswer);
                imageQuestionData.put(questionId, newImageQuestion);
            }
        } catch (Exception e) {
            System.out.println("===========================");
            System.out.println("Unable to load all image question");
            System.out.println("===========================");
            e.printStackTrace();
        }
    }

    public void loadAllListeningQuestion() {
        String sqlQuery = "SELECT * FROM `listening_question`";
        try {
            ResultSet resultSet = DBHelper.query(sqlQuery);
            while (resultSet.next()) {
                long questionId = resultSet.getLong(1);
                String question = resultSet.getString(2);
                String audioSrc = resultSet.getString(3);
                String[] options = new String[3];
                for (int i = 1, k = 4; k <= 5; i++, k++) {
                    options[i] = resultSet.getString(k);
                }
                byte correctAnswer = resultSet.getByte(6);
                ListeningQuestion newListeningQuestion = new ListeningQuestion(questionId, question, audioSrc,
                        options[1], options[2], correctAnswer);
                listeningQuestionData.put(questionId, newListeningQuestion);
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("Unable to load all listening question");
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }

    public void loadAllFillQuestion() {
        String sqlQuery = "SELECT * FROM `fill_question`";
        try {
            ResultSet resultSet = DBHelper.query(sqlQuery);
            while (resultSet.next()) {
                long questionId = resultSet.getLong(1);
                String question = resultSet.getString(2);
                String context = resultSet.getString(3);
                String[] options = new String[5];
                for (int i = 1, k = 4; k <= 7; i++, k++) {
                    options[i] = resultSet.getString(k);
                }
                byte correctAnswer = resultSet.getByte(8);
                FillQuestion newFillQuestion = new FillQuestion(questionId, question, context,
                        options[1], options[2], options[3], options[4], correctAnswer);
                fillQuestionData.put(questionId, newFillQuestion);
            }
        } catch (Exception e) {
            System.out.println("================================");
            System.out.println("Unable to load all fill question");
            System.out.println("================================");
            e.printStackTrace();
        }
    }

    public void loadAllTranslateQuestion() {
        String sqlQuery = "SELECT * FROM `translate_question`";
        try {
            ResultSet resultSet = DBHelper.query(sqlQuery);
            while (resultSet.next()) {
                long questionId = resultSet.getLong(1);
                String question = resultSet.getString(2);
                String context = resultSet.getString(3);
                String[] options = new String[5];
                for (int i = 1, k = 4; k <= 7; i++, k++) {
                    options[i] = resultSet.getString(k);
                }
                byte correctAnswer = resultSet.getByte(8);
                TranslateQuestion newTranslateQuestion = new TranslateQuestion(questionId, question, context,
                        options[1], options[2], options[3], options[4], correctAnswer);
                translateQuestionData.put(questionId, newTranslateQuestion);
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("Unable to load all translate question");
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
