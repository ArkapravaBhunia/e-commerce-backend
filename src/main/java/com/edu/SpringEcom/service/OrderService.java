package com.edu.SpringEcom.service;

import com.edu.SpringEcom.model.*;
import com.edu.SpringEcom.model.dto.*;
import com.edu.SpringEcom.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    CouponRepo couponRepo;

    public OrderResponse placeOrder(OrderRequest request) {
        // 1. Validate User
        User user = userRepo.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Validate Address
        Address address = addressRepo.findById(request.addressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Address does not belong to user");
        }

        // 3. Create Order
        Order order = new Order();
        String orderId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(user.getName());
        order.setEmail(user.getEmail());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());
        // For simplicity in this demo, I'm concatenating address to a string,
        // but you could add an Address relation to Order entity too.
        String fullAddress = address.getStreet() + ", " + address.getCity() + ", " + address.getZipCode();
        // Note: You might want to add a 'shippingAddress' field to your Order entity.

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal subTotal = BigDecimal.ZERO;

        for (OrderItemRequest itemReq : request.items()) {
            Product product = productRepo.findById(itemReq.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStockQuantity() < itemReq.quantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getTitle());
            }

            product.setStockQuantity(product.getStockQuantity() - itemReq.quantity());
            productRepo.save(product);

            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(itemReq.quantity()));
            subTotal = subTotal.add(itemTotal);

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemReq.quantity())
                    .totalPrice(itemTotal)
                    .order(order)
                    .build();

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);

        // 4. Apply Coupon
        BigDecimal finalTotal = subTotal;
        if (request.couponCode() != null && !request.couponCode().isEmpty()) {
            Coupon coupon = couponRepo.findByCode(request.couponCode())
                    .orElseThrow(() -> new RuntimeException("Invalid Coupon"));

            if (!coupon.isActive() || (coupon.getExpiryDate() != null && coupon.getExpiryDate().isBefore(LocalDate.now()))) {
                throw new RuntimeException("Coupon expired or inactive");
            }

            BigDecimal discount = subTotal.multiply(coupon.getDiscountPercentage()).divide(BigDecimal.valueOf(100));
            finalTotal = subTotal.subtract(discount);
        }

        // Save Order
        Order saveOrder = orderRepo.save(order);

        return buildOrderResponse(saveOrder);
    }

    public List<OrderResponse> getAllOrderResponses() {
        List<Order> orders = orderRepo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(buildOrderResponse(order));
        }
        return orderResponses;
    }

    private OrderResponse buildOrderResponse(Order order) {
        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for (OrderItem item : order.getOrderItems()) {
            OrderItemResponse orderItemResponse = new OrderItemResponse(
                    item.getProduct().getTitle(),
                    item.getQuantity(),
                    item.getTotalPrice());
            itemResponses.add(orderItemResponse);
        }
        return new OrderResponse(
                order.getOrderId(),
                order.getCustomerName(),
                order.getEmail(),
                order.getStatus(),
                order.getOrderDate(),
                itemResponses);
    }
}