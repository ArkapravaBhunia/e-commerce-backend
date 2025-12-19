package com.edu.SpringEcom.controller;

import com.edu.SpringEcom.model.Address;
import com.edu.SpringEcom.model.User;
import com.edu.SpringEcom.model.dto.AuthRequest;
import com.edu.SpringEcom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        try {
            User user = userService.register(request);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            User user = userService.login(request.email(), request.password());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/{userId}/addresses")
    public ResponseEntity<?> addAddress(@PathVariable Long userId, @RequestBody Address address) {
        try {
            Address savedAddress = userService.addAddress(userId, address);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Helper to check for coupons
    @GetMapping("/coupons/{code}")
    public ResponseEntity<?> validateCoupon(@PathVariable String code) {
        // In a real app, inject CouponService. For now, simple mock or repo call
        // This is a placeholder to prevent frontend 404 if you try to validate
        return new ResponseEntity<>("Valid", HttpStatus.OK);
    }
}