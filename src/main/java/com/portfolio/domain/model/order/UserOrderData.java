package com.portfolio.domain.model.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserOrderData {
    private Long id;
    private String orderNumber;

    private List<OrderDetailData> orderDetailData;

}
