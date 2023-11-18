package com.dga.equiz.model;

public class PairWord {
    private String wordName;
    private String meaning;

     public PairWord(String wordName, String meaning) {
        this.wordName = wordName;
        this.meaning = meaning;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }
}
