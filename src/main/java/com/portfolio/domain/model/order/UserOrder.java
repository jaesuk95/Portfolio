package com.portfolio.domain.model.order;

import com.portfolio.domain.model.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserOrder {

    // 쿠폰은 생략합니다

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "userOrder",  // mappedBy 를 통해 주인이 아님을 설정
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Column(unique = true)
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserOrderStatus orderStatus;

    private int totalPrice;

    private LocalDateTime serverCancelledAt;

    public void orderCancelRequest(String cancelReason) {
        this.serverCancelledAt = LocalDateTime.now();
        this.cancelReason = cancelReason;
    }

    private String cancelled_at;
    private String cancelReason;    // Lob?

    private String receipt_id;      // OrderBootPay DTO 에서 사용하는 객체, 부트페이에서 발급하는 고유 영수증 ID
    private String receipt_url;
    private String method;          // 결제된 수단 Alias ( ex. card, vbank, bank, phone )
    private String method_name;     // 결제된 수단의 명칭
    private String pg;              // 결제된 PG의 Alias ( ex. danal, inicis, kcp )
    private String pg_name;         // 결제된 PG사의 명칭
    private String purchased_at;    // 결제 승인이 된 시각 ( 한국 기준시 +09:00 )
    private String revoked_at;      // 결제가 취소된 시각 ( 한국 기준시 +09:00 )
    private Long status;             // 결제 상태, 현재 결제의 상태를 나타냅니다.

    public UserOrder(User user, List<OrderDetail> orderDetailList) {
        this.user = user;
        this.orderDetailList = orderDetailList;
        this.orderStatus = UserOrderStatus.미결제;
    }

    public void createUserOrderNumber() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        this.orderNumber = date + id;
    }

    public void setBootPayResults(String receipt_url,
                                  Long status,
                                  String pg,
                                  String pg_name,
                                  String method,
                                  String method_name,
                                  String receipt_id,
                                  String purchased_at,
                                  UserOrderStatus userOrderStatus) {
        this.receipt_url = receipt_url;
        this.status = status;
        this.pg = pg;
        this.pg_name = pg_name;
        this.method = method;
        this.method_name = method_name;
        this.receipt_id = receipt_id;
        this.purchased_at = purchased_at;
        this.orderStatus = userOrderStatus;
    }

    public void updateDetailStatus() {
        List<OrderDetail> detailList = orderDetailList;
        for (OrderDetail orderDetail : detailList) {
            orderDetail.updateDetailStatus(UserOrderStatus.주문준비중);
        }
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public void setBootPayCancelResults(String cancelled_at, long status) {
        this.cancelled_at = cancelled_at;
        this.status = status;
    }
}
