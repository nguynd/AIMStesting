package com.ISD.AIMS.dto;

import java.util.List;

/**
 * DTO này dùng để biểu diễn toàn bộ giỏ hàng khi trả về cho client.
 */
public class CartDto {
    private Long id;
    private Long userId;
    private List<CartItemDto> items;
    private double totalPrice;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<CartItemDto> getItems() { return items; }
    public void setItems(List<CartItemDto> items) { this.items = items; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}