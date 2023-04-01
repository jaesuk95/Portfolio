package com.portfolio.domain.model.naver;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shippingPolicy")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class NaverPayShippingPolicyXml {
    private String groupId;
    private String method;
    private String feeType;
    private String feePayType;
    private String feePrice;
    private SurchargeByArea surchargeByArea = new SurchargeByArea();


    @XmlRootElement(name = "surchargeByArea")
    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    @NoArgsConstructor
    public static class SurchargeByArea {
        private String apiSupport = "false";
        private String splitUnit = "2";
        private String area2Price = "3000";

    }



}


