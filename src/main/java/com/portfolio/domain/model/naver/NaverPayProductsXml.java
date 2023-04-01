package com.portfolio.domain.model.naver;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class NaverPayProductsXml {
//    @XmlElement(name = "option")
    @XmlElement(name = "product")
    private List<NaverPayProductXml> productXmlList;


}


