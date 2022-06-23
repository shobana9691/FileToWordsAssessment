package com.nordea.filetowords.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@Component
public class CSVGenerate {

    List<String> sequenceOfWordData ;
    List<List<String>> sentenceDataListMax;
}
