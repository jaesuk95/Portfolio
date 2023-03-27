package com.portfolio.web.apis;

import com.portfolio.domain.common.AddressRegisterCommand;
import com.portfolio.domain.common.AddressSearchCommand;
import com.portfolio.domain.model.address.AddressData;
import com.portfolio.domain.model.address.AddressService;
import com.portfolio.web.payload.AddressRegisterPayload;
import com.portfolio.web.results.ApiResult;
import com.portfolio.web.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AddressApiController extends AbstractBaseController{

    private final AddressService addressService;

    @PostMapping("/api/address")
    public ResponseEntity<ApiResult> registerAddress(
            @RequestBody AddressRegisterPayload payload,
            HttpServletRequest request) {
        try {
            AddressRegisterCommand command = payload.toCommand();
            addTriggeredBy(command, request);
            Long id = addressService.register(command);
            return Result.ok(String.valueOf(id));
        } catch (Exception e) {
            return Result.failure("실패");
        }
    }

    @GetMapping("/api/address")
    public ResponseEntity<ApiResult> getAddresses(HttpServletRequest request) {
        try {
            AddressSearchCommand command = new AddressSearchCommand();
            addTriggeredBy(command,request);
            List<AddressData> address = addressService.getAddress(command);
            return Result.ok(ApiResult.data(address));
        } catch (Exception e) {
            return Result.failure("실패");
        }
    }

}
