package com.portfolio.domain.model.naver;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class NaverPayInterfaceXML {
    private String salesCode;
    private String cpaInflowCode;
    private String naverInflowCode;
    private String saClickId;
}
