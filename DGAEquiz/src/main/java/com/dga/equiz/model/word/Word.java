package com.dga.equiz.model.word;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Word {
    private String word;
    private String phonetic;
    private List<Phonetic> phonetics = new ArrayList<Phonetic>();
    private List<Meaning> meanings = new ArrayList<Meaning>();

    public Word() {

    }

    public Word(String word, String phonetic, List<Phonetic> phonetics, List<Meaning> meanings) {
        this.word = word;
        this.phonetic = phonetic;
        this.phonetics = phonetics;
        this.meanings = meanings;
    }

    //region Getter and Setter
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public List<Phonetic> getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(List<Phonetic> phonetics) {
        this.phonetics = phonetics;
    }

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
    }
    //endregion
}
