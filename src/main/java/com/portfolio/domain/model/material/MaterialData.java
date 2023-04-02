package com.portfolio.domain.model.material;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaterialData {
    private Long id;
    private String materialName;
    private String surfaceName;
}
