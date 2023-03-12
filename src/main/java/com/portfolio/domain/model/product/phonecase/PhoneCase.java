package com.portfolio.domain.model.product.phonecase;

import com.portfolio.domain.model.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "ID")      // product 의 아이디를 참조한다.
@DiscriminatorValue("PHONE_CASE")
public class PhoneCase extends Product {

    // db 에 phoneType 을 각자 저장하는 이유: 모델마다 가격이 다르거나, 이름이 다를수 있기 때문에
    private PhoneType phoneType;

    @Builder
    public PhoneCase(String name, int price, PhoneType phoneType) {
        super(name,price);
        this.phoneType = phoneType;
    }

}
