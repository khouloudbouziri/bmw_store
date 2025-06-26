package com.example.advanced_java_project.Services;

import com.example.advanced_java_project.Models.Cart;
import com.example.advanced_java_project.Models.CartItem;
import com.example.advanced_java_project.Models.Vehicle;
import com.example.advanced_java_project.Repositories.CartRepository;
import com.example.advanced_java_project.Repositories.UserRepository;
import com.example.advanced_java_project.Repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private CartRepository cartRepository;
    private UserRepository userRepository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public CartService(CartRepository cartRepository, UserRepository userRepository,
                       VehicleRepository vehicleRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public Cart getUserCart(Long userId) {
        return cartRepository.getByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId)));
                    return cartRepository.save(newCart);
                });
    }

    @Transactional
    public Cart addItem(Long userId, Long vehicleId, int quantity) {
        Cart cart = getUserCart(userId);
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));
        if(vehicle.getQuantity() < quantity)
            throw new RuntimeException("Not enough stock for vehicle ID: " + vehicleId);

        vehicle.setQuantity(vehicle.getQuantity() - quantity);

        cart.getItems().stream()
                .filter(item -> item.getVehicle().getId().equals(vehicleId))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.setQuantity(item.getQuantity() + quantity),
                        () -> {
                            CartItem cartItem = new CartItem();
                            cartItem.setCart(cart);
                            cartItem.setVehicle(vehicle);
                            cartItem.setQuantity(quantity);
                            cart.getItems().add(cartItem);
                        }
                );
        vehicleRepository.save(vehicle);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart updateQuantity(Long userId, Long vehicleId, int quantity){
        Cart cart = cartRepository.getByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));

        CartItem item = cart.getItems().stream()
                .filter(ci -> ci.getVehicle().getId().equals(vehicleId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Item not found in cart"));

        int updatedQuantity = quantity - item.getQuantity();

        if(updatedQuantity > 0){
            if(vehicle.getQuantity() < updatedQuantity)
                throw new RuntimeException("Not enough stock for vehicle " + vehicleId);
            vehicle.setQuantity(vehicle.getQuantity() - updatedQuantity);
        }else if(updatedQuantity < 0){
            vehicle.setQuantity(vehicle.getQuantity() + (-updatedQuantity));
        }

        if ((quantity == 0)) {
            cart.getItems().remove(item);
        } else {
            item.setQuantity(quantity);
        }

        vehicleRepository.save(vehicle);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeItem(Long userId, Long vehicleId) {
        Cart cart = getUserCart(userId);
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));

        CartItem item = cart.getItems().stream()
                .filter(cartItem -> cartItem.getVehicle().getId().equals(vehicleId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Item not found in cart"));

        vehicle.setQuantity(vehicle.getQuantity() + item.getQuantity());

        cart.getItems().remove(item);

        vehicleRepository.save(vehicle);
        return cartRepository.save(cart);
    }
}
