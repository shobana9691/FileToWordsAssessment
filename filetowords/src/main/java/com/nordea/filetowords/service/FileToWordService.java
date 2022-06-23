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
     *
     * @param file - input file of text
     * @return TextData of model
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
     *
     * @param wordsInSentence - array of strings of words
     * @param sequenceOfWords - list of words in a sentence
     * @param sequenceOfWordData -list of words in a sentence to manipulate hearder for csv
     */
    public void computeWordsFromSentence(String[] wordsInSentence, List<String> sequenceOfWords, List<String> sequenceOfWordData);

    /**
     * Method to generate CSV file
     *
     * @param max - count of words
     * @param cSVGenerate - CsvGenerate model object to build csv
     */
    public void generateCSV(Integer max, CSVGenerate cSVGenerate);

    /**
     * Sorts the computed  words from Sentence - extracted from text
     *
     * @param sentenceDataList  - list of sentences
     * @param sentenceDataListMax - list of sentences with list of words
     * @param csvGenerate - CsvGenerate model object used to generate csv
     * @param sentenceObj -  sentence model object used to build structure for xml
     * @param sequenceOfWords - words in a sentence
     * @param sequenceOfWordData - words in a sentence to manipulate hearder for csv
     */
    public void computeSortWords(List<Sentence> sentenceDataList, List<List<String>> sentenceDataListMax, CSVGenerate csvGenerate, Sentence sentenceObj, List<String> sequenceOfWords, List<String> sequenceOfWordData);


    /**
     * Method to generate XML file
     *
     * @param textData - TextData model object used to create XML structure
     * @return path - absolute path of XML file
     */
    public String generateXML(TextData textData);
}
