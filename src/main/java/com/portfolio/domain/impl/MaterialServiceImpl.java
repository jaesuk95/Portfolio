package com.portfolio.domain.impl;

import com.portfolio.domain.common.MaterialRegisterCommand;
import com.portfolio.domain.model.material.Material;
import com.portfolio.domain.model.material.MaterialRepository;
import com.portfolio.domain.model.material.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
    public Long registerByAdmin(MaterialRegisterCommand command) {
        String materialName = command.getMaterialName();
        String surfaceName = command.getSurfaceName();

        Material material = new Material(
                materialName,
                surfaceName
        );
        materialRepository.save(material);
        return material.getId();
    }
}
