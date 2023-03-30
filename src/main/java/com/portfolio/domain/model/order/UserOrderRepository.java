package com.portfolio.domain.model.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserOrderRepository extends JpaRepository<UserOrder,Long> {
    // 굳이 JPQL 을 여기서 사용할 필요는 없지만, JPQL 활용법을 표현하고 싶었다
    @Query("select o from UserOrder o " +
            "where o.orderNumber = :orderNumber")
    UserOrder findByOrderNumberJPQL(@Param("orderNumber") String orderNumber);

    @Query("select o from UserOrder o " +
            "where o.orderNumber = :orderNumber and " +
            "o.user.id = :userId")
    UserOrder findMyOrderJPQL(@Param("orderNumber") String orderNumber,
                              @Param("userId") Long userId);
}
