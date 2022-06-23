package com.nordea.filetowords.service;

import com.nordea.filetowords.model.CSVGenerate;
import com.nordea.filetowords.model.Sentence;
import com.nordea.filetowords.model.TextData;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileToWordService {

    /**
     * Method process input file and returns model object in a structure as expected in output
     * @param file
     * @return
     */
    public TextData computeLines(File file) throws IOException;
    /**
     * Sorts the computed  words from Sentence - extracted from text
     * @param sentenceDataList
     * @param sentenceDataListMax
     * @param csvGenerate
     * @param sentenceObj
     * @param sequenceOfWords
     * @param sequenceOfWordData
     */


    /**
            * Computes the  words from Sentence - extracted from text
     * @param wordsInSentence
     * @param sequenceOfWords
     * @param sequenceOfWordData
     */
    public void computeWordsFromSentence(String[] wordsInSentence, List<String> sequenceOfWords, List<String> sequenceOfWordData);

    /**
     * Method to generate CSV file
     * @param max
     * @param cSVGenerate
     * @throws IOException
     */
    public void generateCSV(Integer max, CSVGenerate cSVGenerate) ;

        public void computeSortWords(List<Sentence> sentenceDataList, List<List<String>> sentenceDataListMax, CSVGenerate csvGenerate, Sentence sentenceObj, List<String> sequenceOfWords, List<String> sequenceOfWordData);


        /**
         * Method to generate XML file
         * @param textData
         * @return
         */
    public String generateXML(TextData textData);
}
