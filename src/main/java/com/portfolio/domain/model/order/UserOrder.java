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

    private UserOrderStatus orderStatus;

    public UserOrder(User user, List<OrderDetail> orderDetailList) {
        this.user = user;
        this.orderDetailList = orderDetailList;
        this.orderStatus = UserOrderStatus.미결제;
    }

    public void createUserOrderNumber() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        this.orderNumber = date + id;
    }
}
