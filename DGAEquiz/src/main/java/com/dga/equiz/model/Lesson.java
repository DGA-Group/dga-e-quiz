package com.dga.equiz.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lesson {
    private List<Long> image_questions_id;
    private List<Long> fill_questions_id;
    private List<Long> listening_questions_id;
    private List<Long> translate_questions_id;

    public Lesson() {

    }

    public Lesson(List<Long> image_questions_id, List<Long> fill_questions_id,
                  List<Long> listening_questions_id, List<Long> translate_questions_id) {
        this.image_questions_id = image_questions_id;
        this.fill_questions_id = fill_questions_id;
        this.listening_questions_id = listening_questions_id;
        this.translate_questions_id = translate_questions_id;
    }

    public List<Long> getImage_questions_id() {
        return image_questions_id;
    }

    public void setImage_questions_id(List<Long> image_questions_id) {
        this.image_questions_id = image_questions_id;
    }

    public List<Long> getFill_questions_id() {
        return fill_questions_id;
    }

    public void setFill_questions_id(List<Long> fill_questions_id) {
        this.fill_questions_id = fill_questions_id;
    }

    public List<Long> getListening_questions_id() {
        return listening_questions_id;
    }

    public void setListening_questions_id(List<Long> listening_questions_id) {
        this.listening_questions_id = listening_questions_id;
    }

    public List<Long> getTranslate_questions_id() {
        return translate_questions_id;
    }

    public void setTranslate_questions_id(List<Long> translate_questions_id) {
        this.translate_questions_id = translate_questions_id;
    }
}
