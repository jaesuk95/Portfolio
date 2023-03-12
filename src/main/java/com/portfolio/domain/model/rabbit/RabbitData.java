package com.portfolio.domain.model.rabbit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RabbitData implements Serializable {

    private static final long serialVersionUID = 2735934754929614868L;
    private Map<String,Object> variables;

}
