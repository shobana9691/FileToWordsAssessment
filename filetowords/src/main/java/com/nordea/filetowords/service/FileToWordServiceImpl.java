package com.nordea.filetowords.service;

import com.nordea.filetowords.FileToWordsApplication;
import com.nordea.filetowords.model.CSVGenerate;
import com.nordea.filetowords.model.Sentence;
import com.nordea.filetowords.model.TextData;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.nordea.filetowords.util.Utility.*;



@Service
public class FileToWordServiceImpl implements FileToWordService {

    private static final Logger log =  LoggerFactory.getLogger(FileToWordServiceImpl.class);

    /**
     * Method process input file and returns model object in a structure as expected in output
     * @param file
     * @return
     */
    public TextData computeLines(File file) throws IOException {
        TextData textData = new TextData();
      //  try {
        List<Sentence> sentenceDataList = new ArrayList<>();
        List<List<String>> sentenceDataListMax = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        CSVGenerate csvGenerate = new CSVGenerate();
            Map<Sentence, List<String>> myMaps = new HashMap<Sentence, List<String>>();

            List<String> linesList  = Files.lines(file.toPath()).collect(Collectors.toList());

        String textFileString = linesList.stream().collect(Collectors.joining());
        String[] sentencesSplittedWithDelimiter = getSentencesFunction.apply(textFileString);
        Arrays.stream(sentencesSplittedWithDelimiter)
                .filter(removeEmptyStringPredicate())
                .forEach(
                sentence -> {
                        Sentence sentenceObj = new Sentence();
                        String sentencesWithoutSplCharacter = sentence.replaceAll(REGEX_TO_REMOVE_SPL_CHARS,EMPTY_STRING );
                        String[] wordsInSentence = sentencesWithoutSplCharacter.split(WHITE_SPACE);
                        List<String> sequenceOfWords = new ArrayList<>();
                        List<String> sequenceOfWordData = new ArrayList<>();

                        computeWordsFromSentence(wordsInSentence, sequenceOfWords, sequenceOfWordData);
                        computeSortWords(sentenceDataList, sentenceDataListMax, csvGenerate, sentenceObj, sequenceOfWords, sequenceOfWordData);
                        myMaps.put(sentenceObj, sequenceOfWords);

                }
        );

        csvGenerate.setSentenceDataListMax(sentenceDataListMax);
        Integer max = maxWorkCount.apply(sentenceDataListMax, list);
        generateCSV(max,csvGenerate);
        textData.setSentenceList(sentenceDataList);




        log.info("Input file is processed successfully." );
        return textData;
     }

    /**
     * Sorts the computed  words from Sentence - extracted from text
     * @param sentenceDataList
     * @param sentenceDataListMax
     * @param csvGenerate
     * @param sentenceObj
     * @param sequenceOfWords
     * @param sequenceOfWordData
     */
    public void computeSortWords(List<Sentence> sentenceDataList, List<List<String>> sentenceDataListMax, CSVGenerate csvGenerate, Sentence sentenceObj, List<String> sequenceOfWords, List<String> sequenceOfWordData) {
        Collections.sort(sequenceOfWords, String::compareToIgnoreCase);
        Collections.sort(sequenceOfWordData, String::compareToIgnoreCase);
        csvGenerate.setSequenceOfWordData(sequenceOfWordData);
        sentenceObj.setWordsList(sequenceOfWords);
        sentenceDataList.add(sentenceObj);
        sentenceDataListMax.add(sequenceOfWordData);
    }

    /**
     * Computes the  words from Sentence - extracted from text
     * @param wordsInSentence
     * @param sequenceOfWords
     * @param sequenceOfWordData
     */
    public void computeWordsFromSentence(String[] wordsInSentence, List<String> sequenceOfWords, List<String> sequenceOfWordData) {
        Arrays.stream(wordsInSentence)
             .filter(removeEmptyStringPredicate())
             .forEach( words ->
             {
                 sequenceOfWords.add(words);
                 sequenceOfWordData.add(words);
             });
    }

    /**
     * Method to generate CSV file
     * @param max
     * @param cSVGenerate
     * @throws IOException
     */
    public void generateCSV(Integer max, CSVGenerate cSVGenerate) {


        File file = new File(CSV_FILE);
        int index = 1;
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(file),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER
                    ,CSVWriter.NO_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);


        String[] wordArray = new String[max];

        for(int i = 1 ;i<max ;i++)
        {
            wordArray[i] =WORD_STRING+i ;
        }
        writer.writeNext(wordArray);

        for( List<String> s: cSVGenerate.getSentenceDataListMax())
        {

            List<String> valueSequenceOfWordData = s;
            valueSequenceOfWordData.add(0,SENTENCE_STRING+index);
            String[] myArray = new String[valueSequenceOfWordData.size()];
            valueSequenceOfWordData.toArray(myArray);
            writer.writeNext(myArray);
            index ++ ;
        }
        writer.close();
        System.out.println("CSV File is generated and saved in : "+ "\n"+ file.getAbsolutePath());
        log.info("CSV File is generated and saved in : "+ file.getAbsolutePath() );
        }   catch (IOException e) {
            log.error("Error in processing file is  " + e.getMessage(), e);
        }
    }

    /**
     * Method to generate XML file
     * @param textData
     * @return
     */
    public String generateXML(TextData textData)
    {
        String path ="";
        try
        {

            File file = new File(XML_FILE);
            JAXBContext jaxbContext = JAXBContext.newInstance(TextData.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(textData, file);
            path = file.getAbsolutePath();


        } catch (JAXBException e) {
            log.error("Error in generating XML and exception is  " + e.getMessage(), e);
        }
        return path;
    }

    /**
     * BiFunction to compute max word count in a given sentence
     */
    BiFunction<List<List<String>>, List<Integer>, Integer> maxWorkCount = (sentenceDataListMax, list) -> {
        sentenceDataListMax.forEach(data -> list.add(data.size()));
        Integer max = Collections.max(list);
        max = max-1 ;
        return max;
    };


    Function<String, String[]> getSentencesFunction = textString -> textString.split(REGEX_TO_SPLIT_SENTENCES);

    private Predicate<String> removeEmptyStringPredicate() {
        return str -> !str.isEmpty();
    }

}
