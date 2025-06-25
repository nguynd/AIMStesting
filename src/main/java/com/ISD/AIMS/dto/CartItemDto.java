package com.ISD.AIMS.dto;

/**
 * DTO này dùng để biểu diễn một món hàng trong giỏ khi trả về cho client.
 * Chỉ chứa các thông tin cần thiết để hiển thị.
 */
public class CartItemDto {
    private Long id;
    private int quantity;
    private Long productId;
    private String productTitle;
    private double productPrice;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductTitle() { return productTitle; }
    public void setProductTitle(String productTitle) { this.productTitle = productTitle; }

    public double getProductPrice() { return productPrice; }
    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }
}