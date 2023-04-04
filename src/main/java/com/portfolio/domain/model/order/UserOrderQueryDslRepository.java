package com.portfolio.domain.model.order;

import com.portfolio.domain.common.querydsl.Querydsl4RepositorySupport;
import com.portfolio.domain.model.address.AddressData;
import com.portfolio.domain.model.address.QAddress;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserOrderQueryDslRepository extends Querydsl4RepositorySupport {
    public UserOrderQueryDslRepository() {
        super(UserOrder.class);
    }

    public UserOrderData getUserOrderV1(String orderNumber, Long user_id) {
        List<OrderDetailData> orderDetailData = select(
                Projections.bean(OrderDetailData.class,
                        QOrderDetail.orderDetail.id,
                        QOrderDetail.orderDetail.productPrice,
                        Projections.bean(AddressData.class,
                                QOrderDetail.orderDetail.address.addressDetail,
                                QOrderDetail.orderDetail.address.addressName,
                                QOrderDetail.orderDetail.address.recipientName,
                                QOrderDetail.orderDetail.address.recipientPhone,
                                QOrderDetail.orderDetail.address.zipcode
                        ).as("addressData")))
                .from(QOrderDetail.orderDetail)
                .join(QOrderDetail.orderDetail.address, QAddress.address)
                .where(QOrderDetail.orderDetail.userOrder.orderNumber.eq(orderNumber),
                        QOrderDetail.orderDetail.userOrder.user.id.eq(user_id))
                .fetch();

        UserOrderData userOrderData = select(
                Projections.bean(UserOrderData.class,
                        QUserOrder.userOrder.id,
                        QUserOrder.userOrder.orderNumber,
                        QUserOrder.userOrder.totalPrice))
                .from(QUserOrder.userOrder)
                .where(QUserOrder.userOrder.orderNumber.eq(orderNumber))
                .fetchOne();

        if (userOrderData != null) {
            userOrderData.setOrderDetailData(orderDetailData);
        }
        return userOrderData;
    }

    public List<UserOrderData> getUserOrders(Long user_id) {
        List<UserOrderData> userOrderDataList = select(
                Projections.bean(UserOrderData.class,
                        QUserOrder.userOrder.id,
                        QUserOrder.userOrder.orderNumber,
                        QUserOrder.userOrder.totalPrice))
                .from(QUserOrder.userOrder)
                .where(QUserOrder.userOrder.user.id.eq(user_id))
                .fetch();

        List<String> orderNumberList = userOrderDataList.stream()
                .map(UserOrderData::getOrderNumber)
                .collect(Collectors.toList());

        Map<String, List<OrderDetailData>> transform = getQueryFactory()
                .from(QOrderDetail.orderDetail)
                .join(QOrderDetail.orderDetail.address, QAddress.address)
                .orderBy(QOrderDetail.orderDetail.id.desc())
                .where(QOrderDetail.orderDetail.userOrder.orderNumber.in(orderNumberList))
                .transform(GroupBy.groupBy(QOrderDetail.orderDetail.userOrder.orderNumber)
                        .as(GroupBy.list(Projections.bean(OrderDetailData.class,
                                QOrderDetail.orderDetail.id,
                                QOrderDetail.orderDetail.productPrice,
                                Projections.bean(AddressData.class,
                                        QOrderDetail.orderDetail.address.addressDetail,
                                        QOrderDetail.orderDetail.address.addressName,
                                        QOrderDetail.orderDetail.address.recipientName,
                                        QOrderDetail.orderDetail.address.recipientPhone,
                                        QOrderDetail.orderDetail.address.zipcode
                                ).as("addressData")))));

        for (UserOrderData userOrderData : userOrderDataList) {
            List<OrderDetailData> orderDetailData = transform.get(userOrderData.getOrderNumber());
            userOrderData.setOrderDetailData(orderDetailData);
        }
        return userOrderDataList;
    }

}
