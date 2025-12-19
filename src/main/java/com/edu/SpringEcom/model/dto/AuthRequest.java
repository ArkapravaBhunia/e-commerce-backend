package com.edu.SpringEcom.model.dto;

public record AuthRequest(String email, String password, String name, String phone) {
}