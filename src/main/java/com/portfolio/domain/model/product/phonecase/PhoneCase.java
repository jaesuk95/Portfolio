package com.portfolio.domain.model.product.phonecase;

import com.portfolio.domain.model.product.Product;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Entity
@PrimaryKeyJoinColumn(name = "ID")      // product 의 아이디를 참조한다.
@DiscriminatorValue("PHONE_CASE")
public class PhoneCase extends Product {

    private PhoneType phoneType;

}
