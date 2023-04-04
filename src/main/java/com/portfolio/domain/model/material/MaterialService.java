package com.portfolio.domain.model.material;

import com.portfolio.domain.common.MaterialRegisterCommand;

public interface MaterialService {
    Long registerByAdmin(MaterialRegisterCommand command);
}
