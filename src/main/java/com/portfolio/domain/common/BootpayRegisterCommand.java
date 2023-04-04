package com.portfolio.domain.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BootpayRegisterCommand extends UserCommand{
    private String orderNumber;
    private String cardNumber;
    private String cardPw;  //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
    private String cardExpireYear; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
    private String cardExpireMonth; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
    private String cardIdentityNo; //생년월일 또는 사업자 등록번호 (- 없이 입력)
}
