package com.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
* @author FedericoL
*/
@XmlRootElement(name="question")
public class QuestionRecordBean {

    public String text;
    public boolean mandatory;

    @XmlAttribute
    public String attribute;

    public static QuestionRecordBean createQuestion() {
        QuestionRecordBean question = new QuestionRecordBean();
        question.text = "Do you drive more than once a week?";
        question.mandatory = true;
        question.attribute = "at_least_weekly";
        return question;
    }

    public QuestionRecordBean(){

    }
}
