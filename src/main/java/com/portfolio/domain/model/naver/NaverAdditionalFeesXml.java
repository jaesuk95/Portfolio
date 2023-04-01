package com.portfolio.domain.model.naver;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "additionalFees")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class NaverAdditionalFeesXml {

//    private AdditionalFee additionalFee = new AdditionalFee();
    private List<AdditionalFee> additionalFee;

    @XmlRootElement(name = "additionalFee")
    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    @NoArgsConstructor
    public static class AdditionalFee {
        private String id;
        private String surprice;
    }

}
