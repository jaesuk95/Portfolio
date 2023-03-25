package com.portfolio.domain.impl;

import com.portfolio.domain.common.UserOrderRegisterCommand;
import com.portfolio.domain.model.order.OrderDetail;
import com.portfolio.domain.model.order.UserOrder;
import com.portfolio.domain.model.order.UserOrderRepository;
import com.portfolio.domain.model.order.UserOrderService;
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

    @Override
    public String registerOrder(UserOrderRegisterCommand command) {
        List<UserOrderRegisterPayload.OrderDetailPayload> orderDetailList = command.getDetailCommands();

        List<OrderDetail> orderDetails = new ArrayList<>();

        for (UserOrderRegisterPayload.OrderDetailPayload detail : orderDetailList) {

            Long addressId = detail.getAddressId();
            Long productId = detail.getProductId();
            String optionJson = detail.getOptionJson();

            OrderDetail orderDetail = new OrderDetail(
                    // product,
                    // address,
                    // optionJson
            );

            orderDetails.add(orderDetail);
        }

        Long userId = command.getUserId();
        User user = userRepository.findById(userId).orElseThrow();

        UserOrder userOrder = new UserOrder(user,orderDetails);
        userOrderRepository.save(userOrder);

        // TODO:
        // 1. userOrderNumber
        // 2. userOrderDetailNumber
        // 3. userOrderDetail orderNumber (JPA relationship)

        return null;
    }
}

