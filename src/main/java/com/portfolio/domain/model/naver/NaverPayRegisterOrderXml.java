package com.portfolio.domain.model.naver;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class NaverPayRegisterOrderXml {

    private String merchantId;

    private String certiKey;


    @XmlElement(name = "product")
    private List<NaverPayProductXml> productXmlList;

    private String backUrl;

    @XmlElement(name = "interface")
    private NaverPayInterfaceXML naverPayInterfaceXML;

}


