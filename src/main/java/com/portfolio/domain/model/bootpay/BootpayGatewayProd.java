package com.portfolio.domain.model.bootpay;

import kr.co.bootpay.Bootpay;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class BootpayGatewayProd implements BootpayGateway {

    private final String application_id;
    private final String private_key;

    public BootpayGatewayProd(
            @Value("${bootpay-application-id}") String application_id,
            @Value("${bootpay-private-key}") String private_key) {
        this.application_id = application_id;
        this.private_key = private_key;
    }

    @Override
    public String getToken() {
        Bootpay bootpay = new Bootpay(application_id,private_key);
        try {
            HashMap<String, Object> res = bootpay.getAccessToken();
            if(res.get("error_code") == null) { //success
                System.out.println("goGetToken success: " + res);
                return String.valueOf(res.get("access_token"));
            } else {
                System.out.println("goGetToken false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bootpay getBootpay() {
        Bootpay bootpay = new Bootpay(application_id,private_key);
        try {
            HashMap<String, Object> res = bootpay.getAccessToken();
            if(res.get("error_code") == null) { //success
                bootpay.setToken(String.valueOf(res.get("access_token")));
                return bootpay;
            } else {
                System.out.println("goGetToken false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
