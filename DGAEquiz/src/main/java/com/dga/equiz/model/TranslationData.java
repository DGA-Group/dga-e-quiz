package com.dga.equiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TranslationData {

    @JsonProperty("1")
    private List<List<List<Object>>> translations;

    public List<List<List<Object>>> getTranslations() {
        return translations;
    }

    public void setTranslations(List<List<List<Object>>> translations) {
        this.translations = translations;
    }

    public String getSourceText() {
        if (translations != null && translations.size() > 0) {
            List<List<Object>> firstTranslation = translations.get(0);
            if (firstTranslation != null && firstTranslation.size() > 0) {
                List<Object> translationInfo = firstTranslation.get(0);
                if (translationInfo != null && translationInfo.size() > 0) {
                    return translationInfo.get(0).toString();
                }
            }
        }
        return null;
    }

    public static class WordInfo {
        @JsonProperty("1")
        private String word;

        @JsonProperty("2")
        private String translation;

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getTranslation() {
            return translation;
        }

        public void setTranslation(String translation) {
            this.translation = translation;
        }
    }
}
