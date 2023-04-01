package com.portfolio.domain.model.naver;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class SelectedItemXml {
    private String type;
    private String name;
    @XmlElement(name = "value")
    private OptionItemXml.ValueXml valueXml;

//    @Data
//    @XmlAccessorType(XmlAccessType.FIELD)
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class ValueXml {
//        private String id;
//        private String text;
//    }
}
