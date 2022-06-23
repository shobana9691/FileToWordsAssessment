package com.nordea.filetowords.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CSVGenerate {

    public List<String> getSequenceOfWordData() {
        return sequenceOfWordData;
    }

    public void setSequenceOfWordData(List<String> sequenceOfWordData) {
        this.sequenceOfWordData = sequenceOfWordData;
    }

    List<String> sequenceOfWordData ;

    public List<List<String>> getSentenceDataListMax() {
        return sentenceDataListMax;
    }

    public void setSentenceDataListMax(List<List<String>> sentenceDataListMax) {
        this.sentenceDataListMax = sentenceDataListMax;
    }

    List<List<String>> sentenceDataListMax;
}
