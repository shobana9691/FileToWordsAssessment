package com.nordea.filetowords.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentenceMap {

    Map<Sentence, List<String>> myMaps = new HashMap<Sentence, List<String>>();
    public SentenceMap(String sentnce) {

    }

    public String getSentenceData() {
        return sentenceData;
    }

    public void setSentenceData(String sentenceData) {
        this.sentenceData = sentenceData;
    }

    public String sentenceData;

    @Override
    public String toString() {
        return "SentenceMap{" +
                "sentenceData='" + sentenceData + '\'' +
                '}';
    }
}
