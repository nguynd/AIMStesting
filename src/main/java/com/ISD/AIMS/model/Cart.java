package com.ISD.AIMS.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Định nghĩa mối quan hệ Một-Một (One-to-One) giữa Cart và User.
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    // Định nghĩa cột khóa ngoại trong bảng 'carts' để liên kết tới bảng 'users'.
    @JoinColumn(
            name = "user_id",         // Tên cột khóa ngoại sẽ là 'user_id'.
            nullable = false,       // Cột này không được phép rỗng.
            unique = true           // Đảm bảo mỗi user_id chỉ xuất hiện một lần (bắt buộc cho quan hệ 1-1).
    )
    private User user;

    // Định nghĩa mối quan hệ Một-Nhiều (One-to-Many) giữa Cart và CartItem.
    @OneToMany(
            mappedBy = "cart",                      // Mối quan hệ này được quản lý bởi thuộc tính 'cart' trong lớp CartItem.
            cascade = CascadeType.ALL,            // Mọi thay đổi trên Cart (lưu, xóa) sẽ được áp dụng cho các CartItem con.
            orphanRemoval = true,                 // Nếu một CartItem bị xóa khỏi danh sách này, nó cũng sẽ bị xóa khỏi DB.
            fetch = FetchType.EAGER               // Tải danh sách 'items' ngay lập tức cùng với 'Cart'.
    )
    private List<CartItem> items = new ArrayList<>();

    public Cart() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    // Thêm item vào giỏ hàng
    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
    }

    // Xóa item khỏi giỏ hàng
    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
    }
}
