package com.edu.SpringEcom.service;

import com.edu.SpringEcom.model.Address;
import com.edu.SpringEcom.model.User;
import com.edu.SpringEcom.model.dto.AuthRequest;
import com.edu.SpringEcom.repo.AddressRepo;
import com.edu.SpringEcom.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AddressRepo addressRepo;

    public User register(AuthRequest request) {
        if (userRepo.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setEmail(request.email());
        user.setPassword(request.password()); // In real app, hash this!
        user.setName(request.name());
        user.setPhone(request.phone());
        return userRepo.save(user);
    }

    public User login(String email, String password) {
        return userRepo.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }

    public Address addAddress(Long userId, Address address) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        address.setUser(user);
        return addressRepo.save(address);
    }
}