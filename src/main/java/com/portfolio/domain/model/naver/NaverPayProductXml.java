package com.portfolio.domain.model.naver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class NaverPayProductXml {
    private String id;
    private String merchantProductId;
    private String ecMallProductId;
    private String name;
    private String basePrice;
    private String infoUrl;
    private String imageUrl;

    @XmlElement(name = "single")
    private Single single;

    private String stockQuantity;
    private String status;
    private String optionSupport;

    @XmlElement(name = "option")
    private List<OptionXml> optionList;

    @XmlElement(name = "shippingPolicy")
    private NaverPayShippingPolicyXml naverPayShippingPolicyXml;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Single {
        private String quantity;
    }

}


