package com.portfolio.domain.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portfolio.domain.common.BootpayRegisterCommand;
import com.portfolio.domain.common.PaymentCommand;
import com.portfolio.domain.common.UserOrderRegisterCommand;
import com.portfolio.domain.common.bootpay.BootpayPaymentProcess;
import com.portfolio.domain.management.RabbitMQManagement;
import com.portfolio.domain.model.address.Address;
import com.portfolio.domain.model.address.AddressRepository;
import com.portfolio.domain.model.bootpay.PaymentResultData;
import com.portfolio.domain.model.order.*;
import com.portfolio.domain.model.product.phonecase.PhoneCase;
import com.portfolio.domain.model.product.phonecase.PhoneCaseRepository;
import com.portfolio.domain.model.user.User;
import com.portfolio.domain.model.user.UserRepository;
import com.portfolio.web.payload.UserOrderRegisterPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserOrderServiceImpl implements UserOrderService {

    private final UserRepository userRepository;
    private final UserOrderRepository userOrderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final PhoneCaseRepository phoneCaseRepository;
    private final AddressRepository addressRepository;
    private final BootpayPaymentProcess bootpayPaymentProcess;
    private final RabbitMQManagement rabbitMQManagement;

    @Override
    public String registerOrder(UserOrderRegisterCommand command) {
        List<UserOrderRegisterPayload.OrderDetailPayload> orderDetailList = command.getDetailCommands();

        List<OrderDetail> orderDetails = new ArrayList<>();

        for (UserOrderRegisterPayload.OrderDetailPayload detail : orderDetailList) {

            Long addressId = detail.getAddressId();
            Long productId = detail.getProductId();
            String optionJson = detail.getOptionJson();

            Address address = addressRepository.findById(addressId).orElseThrow();
            PhoneCase phoneCase = phoneCaseRepository.findById(productId).orElseThrow();

            OrderDetail orderDetail = new OrderDetail(
                    phoneCase,
                    address,
                    optionJson
            );
            orderDetailRepository.save(orderDetail);
            orderDetail.createOrderDetailNumber();
            orderDetails.add(orderDetail);
        }

        Long userId = command.getUserId();
        User user = userRepository.findById(userId).orElseThrow();

        UserOrder userOrder = new UserOrder(user,orderDetails);
        userOrderRepository.save(userOrder);

        userOrder.createUserOrderNumber();
        return userOrder.getOrderNumber();
    }

    @Override
    public void validatePayment(PaymentCommand command) throws JsonProcessingException {
        UserOrder userOrder = userOrderRepository.findByOrderNumberJPQL(command.getOrderNumber());
        bootpayPaymentProcess.validatePayment(userOrder, command);
        userOrder.updateDetailStatus();

        // 이메일
        rabbitMQManagement.sendUserOrderMessage(userOrder);
    }

    @Override
    public PaymentResultData cancelPayment(PaymentCommand command) throws JsonProcessingException {
        PaymentResultData resultData = new PaymentResultData();

        UserOrder userOrder = userOrderRepository.findMyOrderJPQL(command.getOrderNumber(), command.getUserId());

        if (userOrder.getOrderStatus().equals(UserOrderStatus.취소완료)) {
            resultData.setStatusCode(400);
            resultData.setMessage("이미 취소완료 상태입니다.");
            return resultData;
        }

        userOrder.orderCancelRequest(command.getCancelReason());
        bootpayPaymentProcess.cancelOrder(userOrder,command);
        rabbitMQManagement.sendOrderCancelEmail(userOrder);

        resultData.setStatusCode(200);
        resultData.setMessage("결제취소 완료");
        return resultData;
    }

    @Override
    public void registerBootpayPayment(BootpayRegisterCommand command) {
        User user = userRepository.findById(command.getUserId().value()).orElseThrow();
        bootpayPaymentProcess.requestBillingKey(command,user);
    }

}

