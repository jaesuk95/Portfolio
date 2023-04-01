package com.portfolio.domain.model.naver;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class OptionXml {
    private String quantity;
    private String price;
    private String manageCode;
    @XmlElement(name = "selectedItem")
    private List<SelectedItemXml> selectedItemXml;

    @XmlElement(name = "optionItem")
    private List<OptionItemXml> optionItem;

    @XmlElement(name = "combination")
    private List<CombinationXml> combinationXml;
}
