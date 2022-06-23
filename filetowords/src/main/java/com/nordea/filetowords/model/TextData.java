package com.nordea.filetowords.model;


import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@XmlRootElement(name="text")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextData {


    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }

    @XmlElement(name="sentence")
    private List<Sentence> sentenceList;
}
