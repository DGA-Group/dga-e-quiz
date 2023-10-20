package com.dga.equiz.model;

import com.dga.equiz.model.question.*;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.ApplicationEnum.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private final long lessonNumber;
    private List<Question> questions = new ArrayList<Question>();

    public Lesson(long lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public void addQuestion(QuestionType type, long questionId) {
        switch (type) {
            case FillQuestion: {
                FillQuestion newQuestion = ApplicationData.getInstance().getFillQuestionData().get(questionId);
                if (newQuestion == null) {
                    System.out.println("Unable to add to lesson, fill question id: " + questionId);
                    break;
                }
                questions.add(newQuestion);
            }
            break;
            case ImageQuestion: {
                ImageQuestion newQuestion = ApplicationData.getInstance().getImageQuestionData().get(questionId);
                if (newQuestion == null) {
                    System.out.println("Unable to add to lesson, image question id: " + questionId);
                    break;
                }
                questions.add(newQuestion);
            }
            break;
            case ListeningQuestion: {
                ListeningQuestion newQuestion = ApplicationData.getInstance().getListeningQuestionData().get(questionId);
                if (newQuestion == null) {
                    System.out.println("Unable to add to lesson, listening question id: " + questionId);
                    break;
                }
                questions.add(newQuestion);
            }
            break;
            case TranslateQuestion: {
                TranslateQuestion newQuestion = ApplicationData.getInstance().getTranslateQuestionData().get(questionId);
                if (newQuestion == null) {
                    System.out.println("Unable to add to lesson, translate question id: " + questionId);
                    break;
                }
                questions.add(newQuestion);
            }
            break;
            default:
                break;
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
