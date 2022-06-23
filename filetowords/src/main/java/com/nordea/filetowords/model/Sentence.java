package com.nordea.filetowords.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
@Component
@XmlAccessorType(XmlAccessType.FIELD)
public class Sentence {

   /* public List<String> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<String> wordsList) {
        this.wordsList = wordsList;
    }*/

    @XmlElement(name="word")
    public List<String> wordsList ;

}
