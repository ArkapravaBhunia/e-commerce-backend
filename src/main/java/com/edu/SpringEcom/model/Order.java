package com.edu.SpringEcom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Entity class representing a customer order.
 * 
 * <p>
 * This entity maps to the 'orders' table (avoiding SQL reserved keyword
 * 'order')
 * and contains:
 * <ul>
 * <li>Unique order identifier for customer reference</li>
 * <li>Customer contact information</li>
 * <li>Order status and tracking</li>
 * <li>Associated order items (one-to-many relationship)</li>
 * </ul>
 * </p>
 * 
 * <p>
 * <b>Cascade Behavior:</b> All operations cascade to {@link OrderItem}
 * entities,
 * meaning when an order is saved or deleted, its items are automatically
 * saved or deleted.
 * </p>
 * 
 * @author SpringEcom Team
 * @version 1.0
 * @since 2025-12-14
 * @see OrderItem
 */
@Entity(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
// @Builder
public class Order {

    /**
     * Auto-generated primary key (database internal ID).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique order identifier for customer reference.
     * <p>
     * Format: 8-character alphanumeric string (e.g., "A1B2C3D4")
     * </p>
     */
    @Column(unique = true)
    private String orderId;

    /** Customer's full name. */
    private String customerName;

    /** Customer's email address for order notifications. */
    private String email;

    /**
     * Current order status.
     * <p>
     * Possible values: PLACED, PROCESSING, SHIPPED, DELIVERED, CANCELLED
     * </p>
     */
    private String status;

    /** Date when the order was placed. */
    private LocalDate orderDate;

    /**
     * List of items included in this order.
     * <p>
     * Relationship: One-to-Many with cascade on all operations.
     * </p>
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}
