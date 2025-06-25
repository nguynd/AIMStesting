package com.ISD.AIMS.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ISD.AIMS.model.Cart;
import com.ISD.AIMS.model.CartItem;
import com.ISD.AIMS.model.Product;
import com.ISD.AIMS.model.User;
import com.ISD.AIMS.repository.CartItemRepository;
import com.ISD.AIMS.repository.CartRepository;
import com.ISD.AIMS.repository.ProductRepository;
import com.ISD.AIMS.repository.UserRepository;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public Cart getCartForCurrentUser() {
        User currentUser = getCurrentUser();
        return cartRepository.findByUserId(currentUser.getId())
                .orElseGet(() -> createNewCartForUser(currentUser));
    }
    
    public Cart addProductToCart(Long productId, int quantity) {
        Cart cart = getCartForCurrentUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setCart(cart);
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    public Cart updateItemQuantity(Long cartItemId, int quantity) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + cartItemId));
        validateCartItemOwner(item);
        if (quantity <= 0) {
            cartItemRepository.delete(item);
        } else {
            item.setQuantity(quantity);
            cartItemRepository.save(item);
        }
        return getCartForCurrentUser();
    }

    public Cart removeItemFromCart(Long cartItemId) {
        Cart cart = getCartForCurrentUser();
        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found in your cart with id: " + cartItemId));
        cart.removeItem(itemToRemove);
        return cartRepository.save(cart);
    }
    
    private Cart createNewCartForUser(User user) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        return cartRepository.save(newCart);
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new IllegalStateException("Cannot determine user. User might not be authenticated.");
        }
        String username = ((UserDetails) principal).getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found in database: " + username));
    }

    private void validateCartItemOwner(CartItem item) {
        User currentUser = getCurrentUser();
        if (!item.getCart().getUser().getId().equals(currentUser.getId())) {
            throw new SecurityException("Access Denied: You do not own this cart item.");
        }
    }
}
