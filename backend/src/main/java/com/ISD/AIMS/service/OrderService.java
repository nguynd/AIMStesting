package com.ISD.AIMS.service;

import com.ISD.AIMS.model.*;
import com.ISD.AIMS.repository.CartRepository;
import com.ISD.AIMS.repository.OrderRepository;
import com.ISD.AIMS.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public Order createOrderFromCart(String vnpTxnRef) {
        // Lấy giỏ hàng của người dùng hiện tại
        Cart cart = cartService.getCartForCurrentUser();
        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Giỏ hàng đang trống");
        }

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setVnpTxnRef(vnpTxnRef); // Lưu mã giao dịch để đối chiếu

        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setOrder(order);

            // Cập nhật số lượng tồn kho của sản phẩm
            Product product = cartItem.getProduct();
            int newQuantity = product.getQuantity() - cartItem.getQuantity();
            if (newQuantity < 0) {
                throw new IllegalStateException("Không đủ hàng cho sản phẩm: " + product.getTitle());
            }
            product.setQuantity(newQuantity);
            productRepository.save(product);

            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        // Tính tổng giá trị đơn hàng
        double totalPrice = orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalPrice(totalPrice);
        
        // Xóa giỏ hàng sau khi đã tạo đơn hàng thành công
        cartRepository.delete(cart);

        return orderRepository.save(order);
    }
}