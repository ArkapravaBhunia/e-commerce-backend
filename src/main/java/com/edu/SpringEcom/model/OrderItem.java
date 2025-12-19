package com.edu.SpringEcom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Entity class representing a line item within an order.
 * 
 * <p>
 * This is a junction entity that links {@link Order} and {@link Product},
 * storing order-specific data like quantity and calculated total price.
 * </p>
 * 
 * <p>
 * <b>Relationships:</b>
 * <ul>
 * <li>Many-to-One with {@link Product} - multiple order items can reference
 * same product</li>
 * <li>Many-to-One with {@link Order} - multiple items belong to one order</li>
 * </ul>
 * </p>
 * 
 * @author SpringEcom Team
 * @version 1.0
 * @since 2025-12-14
 * @see Order
 * @see Product
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    /**
     * Auto-generated primary key identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Reference to the ordered product.
     * <p>
     * Eagerly fetched to ensure product details are available.
     * </p>
     */
    @ManyToOne
    private Product product;

    /** Number of units ordered. */
    private int quantity;

    /**
     * Calculated total price for this line item.
     * <p>
     * Formula: product.price × quantity
     * </p>
     */
    private BigDecimal totalPrice;

    /**
     * Reference to the parent order.
     * <p>
     * Lazily fetched to optimize query performance.
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
