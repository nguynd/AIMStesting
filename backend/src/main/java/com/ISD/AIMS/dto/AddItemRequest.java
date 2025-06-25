package com.ISD.AIMS.dto;

/**
 * DTO này dùng để nhận dữ liệu từ client khi họ muốn thêm một sản phẩm vào giỏ.
 */
public class AddItemRequest {
    private Long productId;
    private int quantity;

    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}