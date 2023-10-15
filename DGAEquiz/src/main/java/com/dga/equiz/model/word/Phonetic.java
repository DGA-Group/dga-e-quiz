package com.dga.equiz.model.word;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Phonetic {
    private String text;
    private String audio;
    private String sourceUrl;

    public Phonetic(){

    }

    public Phonetic(String text, String audio, String sourceUrl) {
        this.text = text;
        this.audio = audio;
        this.sourceUrl = sourceUrl;
    }

    //region Getter and Setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
    //endregion
}
