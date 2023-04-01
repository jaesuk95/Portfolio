package com.portfolio.domain.model.naver;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NaverPayConfirmData {
    private String order_id;
    private String order_key;
    private int statusCode;
}
