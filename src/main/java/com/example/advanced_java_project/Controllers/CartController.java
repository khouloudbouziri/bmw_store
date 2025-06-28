package com.example.advanced_java_project.Controllers;

import com.example.advanced_java_project.Dtos.CartItemDTO;
import com.example.advanced_java_project.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/{userId}/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Object> getCart(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cartService.getUserCart(userId));
    }

    @PostMapping("/addToCart")
    public ResponseEntity<Object> addToChart(
            @PathVariable Long userId,
            @RequestBody CartItemDTO cartItemDTO) {
        this.cartService.addItem(userId, cartItemDTO.getVehicleId(), cartItemDTO.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body("Item added to cart successfully");
    }

    @PutMapping("/updateCartItem/{vehicleId}")
    public ResponseEntity<Object> updateCartItemQuantity(
            @PathVariable Long userId,
            @PathVariable Long vehicleId,
            @RequestParam int quantity) {
        this.cartService.updateQuantity(userId, vehicleId, quantity);
        return ResponseEntity.status(HttpStatus.OK).body("Cart item updated successfully");
    }

    @DeleteMapping("/removeFromCart/{vehicleId}")
    public ResponseEntity<Object> removeFromCart(
            @PathVariable Long userId,
            @PathVariable Long vehicleId) {
        this.cartService.removeItem(userId, vehicleId);
        return ResponseEntity.status(HttpStatus.OK).body("Item removed from cart successfully");
    }
}
