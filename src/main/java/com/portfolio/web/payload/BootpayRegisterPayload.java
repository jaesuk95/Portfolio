package com.portfolio.web.payload;

import com.portfolio.domain.common.BootpayRegisterCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BootpayRegisterPayload {
    private String orderNumber;
    private String cardNumber;
    private String cardPw;  //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
    private String cardExpireYear; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
    private String cardExpireMonth; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
    private String cardIdentityNo; //생년월일 또는 사업자 등록번호 (- 없이 입력)

    public BootpayRegisterCommand toCommand() {
        return BootpayRegisterCommand.builder()
                .orderNumber(this.orderNumber)
                .cardNumber(this.cardNumber)
                .cardPw(this.cardPw)
                .cardExpireYear(this.cardExpireYear)
                .cardExpireMonth(this.cardExpireMonth)
                .cardIdentityNo(this.cardIdentityNo)
                .build();
    }
}
