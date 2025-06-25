package com.ISD.AIMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ISD.AIMS.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    // Tìm và trả về tất cả CartItem thuộc về một giỏ hàng cụ thể.
    List<CartItem> findAllByCartId(Long cartId);
}

