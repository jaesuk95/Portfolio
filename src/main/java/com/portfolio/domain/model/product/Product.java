package com.portfolio.domain.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String modelName;
    private int price;
}
