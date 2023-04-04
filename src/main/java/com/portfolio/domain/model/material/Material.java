package com.portfolio.domain.model.material;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    private String materialName;
    private String surfaceName;

    public Material(String materialName, String surfaceName) {
        this.materialName = materialName;
        this.surfaceName = surfaceName;
    }
}
