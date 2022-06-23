package com.nordea.filetowords.model;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Component
@Data
@XmlRootElement(name = "text")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextData {


    @XmlElement(name = "sentence")
    private List<Sentence> sentenceList;
}
