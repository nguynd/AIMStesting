package com.ISD.AIMS.repository;

import com.ISD.AIMS.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Thêm phương thức này để có thể tìm đơn hàng bằng mã giao dịch của VNPAY
    Optional<Order> findByVnpTxnRef(String txnRef);
}